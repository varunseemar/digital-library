package org.VarunSeemar.digital_library.service;

import org.VarunSeemar.digital_library.model.UserModel;
import org.VarunSeemar.digital_library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.VarunSeemar.digital_library.model.UserPrincipal;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel getUserById(long id){
        return this.userRepository.findUserById(id);
    }

    public UserModel addUser(UserModel userModel){
        userModel.setPassword(this.bCryptPasswordEncoder.encode(userModel.getPassword()));
        return this.userRepository.addUser(userModel);
    }

    public UserModel updateUser(UserModel user){
        return this.userRepository.updateUser(user);
    }

    public boolean deleteUserById(long id){
        return this.userRepository.deleteUserById(id);
    }

    public List<UserModel> getAllUsers(){
        return this.userRepository.getAllUsers();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserModel userModel = this.userRepository.findUserByEmail(username);
            return new UserPrincipal(userModel);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

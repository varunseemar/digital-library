package org.VarunSeemar.digital_library.service;

import org.VarunSeemar.digital_library.model.UserModel;
import org.VarunSeemar.digital_library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel getUserById(long id){
        return this.userRepository.findUserById(id);
    }

    public UserModel addUser(UserModel userModel){
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
}

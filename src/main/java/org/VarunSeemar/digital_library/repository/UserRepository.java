package org.VarunSeemar.digital_library.repository;

import org.VarunSeemar.digital_library.entities.output.UserOutputEntity;
import org.VarunSeemar.digital_library.mappers.output.UserOutputMapper;
import org.VarunSeemar.digital_library.model.BookModel;
import org.VarunSeemar.digital_library.model.UserModel;
import org.VarunSeemar.digital_library.repository.jpa.UserJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserRepository {

    private final UserJPARepository userJPARepository;
    private final UserOutputMapper userOutputMapper;

    @Autowired
    public UserRepository(UserJPARepository userJPARepository, UserOutputMapper userOutputMapper) {
        this.userJPARepository = userJPARepository;
        this.userOutputMapper = userOutputMapper;
    }

    public UserModel findUserById(long id){
        Optional<UserOutputEntity> optionalEntity = this.userJPARepository.findById(id);
        return optionalEntity.map(this.userOutputMapper::mapToModel).orElse(null);
    }

    public UserModel addUser(UserModel userModel){
        UserOutputEntity outputEntity = this.userOutputMapper.mapFromModel(userModel);
        UserOutputEntity savedOutputEntity = this.userJPARepository.save(outputEntity);
        return this.userOutputMapper.mapToModel(savedOutputEntity);
    }

    // This is for returning user output entity to the membership mapper.
//    public UserOutputEntity findById(long id){
//        Optional<UserOutputEntity> userOutputEntity= this.userJPARepository.findById(id);
//        return userOutputEntity.orElse(null);
//    }

    public UserModel updateUser(UserModel userModel){
        UserModel user = this.findUserById(userModel.getId());
        userModel.setId(user.getId());
        return this.addUser(userModel);
//        UserOutputEntity outputEntity = this.userOutputMapper.mapFromModel(userModel);
//        UserOutputEntity userOutputEntity = this.userJPARepository.save(outputEntity);
//        return this.userOutputMapper.mapToModel(userOutputEntity);
    }

    public boolean deleteUserById(long id){
        if(this.userJPARepository.existsById(id)){
            this.userJPARepository.deleteById(id);
            return true;
        }
        return false;
//        Optional<UserOutputEntity> outputEntity = this.userJPARepository.findById(id);
//        if(outputEntity.isPresent()){
//            return this.userJPARepository.;
//        }
    }

    public List<UserModel> getAllUsers(){
        List<UserOutputEntity> userOutputEntities = this.userJPARepository.findAll();
        return userOutputEntities.stream().map(this.userOutputMapper::mapToModel).collect(Collectors.toList());
    }

}

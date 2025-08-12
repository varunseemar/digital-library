package org.VarunSeemar.digital_library.adapter;

import org.VarunSeemar.digital_library.commons.CommonAdapter;
import org.VarunSeemar.digital_library.entities.input.UserInputEntity;
import org.VarunSeemar.digital_library.mappers.input.UserInputMapper;
import org.VarunSeemar.digital_library.model.UserModel;
import org.VarunSeemar.digital_library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserAdapter implements CommonAdapter<UserInputEntity, UserModel> {

    private final UserService userService;
    private final UserInputMapper inputMapper;

    @Autowired
    public UserAdapter(UserService userService, UserInputMapper inputMapper) {
        this.userService = userService;
        this.inputMapper = inputMapper;
    }

    public UserModel getUser(long id){
        return this.userService.getUserById(id);
    }

    @Override
    public UserModel save(UserInputEntity userInputEntity) {
        UserModel userModel = this.inputMapper.mapToModel(userInputEntity);
        return this.userService.addUser(userModel);
    }

    @Override
    public UserModel update(UserInputEntity userInputEntity) {
        UserModel userModel = this.inputMapper.mapToModel(userInputEntity);
        return this.userService.updateUser(userModel);
    }

    @Override
    public List<UserModel> getAll() {
        return this.userService.getAllUsers();
    }

    public boolean deleteUser(long id){
        return this.userService.deleteUserById(id);
    }
}

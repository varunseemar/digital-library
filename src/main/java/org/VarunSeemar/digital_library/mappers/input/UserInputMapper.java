package org.VarunSeemar.digital_library.mappers.input;

import org.VarunSeemar.digital_library.entities.input.UserInputEntity;
import org.VarunSeemar.digital_library.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserInputMapper {
    public UserModel mapToModel(UserInputEntity userInputEntity){
        return UserModel.builder()
                .id(userInputEntity.getId())
                .firstName(userInputEntity.getFirstName())
                .lastName(userInputEntity.getLastName())
                .dob(userInputEntity.getDob())
                .email(userInputEntity.getEmail())
                .phoneNumber(userInputEntity.getPhoneNumber())
                .password(userInputEntity.getPassword())
                .build();
    }
}

package org.VarunSeemar.digital_library.mappers.output;
import org.VarunSeemar.digital_library.entities.input.UserInputEntity;
import org.VarunSeemar.digital_library.entities.output.UserOutputEntity;
import org.VarunSeemar.digital_library.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserOutputMapper {
    public UserModel mapToModel(UserOutputEntity userOutputEntity){
        return UserModel.builder()
                .id(userOutputEntity.getId())
                .firstName(userOutputEntity.getFirstName())
                .lastName(userOutputEntity.getLastName())
                .dob(userOutputEntity.getDob())
                .email(userOutputEntity.getEmail())
                .phoneNumber(userOutputEntity.getPhoneNumber())
                .password(userOutputEntity.getPassword())
                .role(userOutputEntity.getRole())
                .build();
    }

    public UserOutputEntity mapFromModel(UserModel userModel){
        return UserOutputEntity.builder()
                .id(userModel.getId())
                .firstName(userModel.getFirstName())
                .lastName(userModel.getLastName())
                .email(userModel.getEmail())
                .dob(userModel.getDob())
                .phoneNumber(userModel.getPhoneNumber())
                .password(userModel.getPassword())
                .role(userModel.getRole())
                .build();
    }
}

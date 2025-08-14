package org.VarunSeemar.digital_library.model;

import lombok.*;
import org.VarunSeemar.digital_library.enums.UserRole;

import java.time.Instant;

@Data
@Builder
@With
@Getter
@Setter
public class UserModel {
    private Long id;
    private String firstName ;
    private String lastName ;
    private Instant dob;
    private String email;
    private String phoneNumber;
    private String password;
    private UserRole role;
}

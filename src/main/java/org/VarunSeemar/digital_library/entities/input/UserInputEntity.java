package org.VarunSeemar.digital_library.entities.input;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Data
@Setter
@Getter
@Builder
public class UserInputEntity {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private Instant dob;
    private String phoneNumber;
    private String password;
}

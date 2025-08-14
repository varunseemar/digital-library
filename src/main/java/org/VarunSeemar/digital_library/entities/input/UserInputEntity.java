package org.VarunSeemar.digital_library.entities.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.VarunSeemar.digital_library.enums.UserRole;

import java.time.Instant;

@Data
@Setter
@Getter
@Builder
public class UserInputEntity {

    private long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    private String email;

    private Instant dob;

    private String phoneNumber;

    @Size(min=8)
    private String password;

    @Builder.Default
    private UserRole role = UserRole.USER;
}

package org.VarunSeemar.digital_library.entities.output;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.VarunSeemar.digital_library.enums.UserRole;

import java.time.Instant;

@Entity
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UserOutputEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id",nullable = false)
    private long id;

    @Column(name = "firstname",length = 100,nullable = false)
    private String firstName;

    @Column(name = "lastname",length = 100)
    private String lastName;

    @Column(name = "dob")
    private Instant dob;

    @Column(name = "email",length = 100)
    private String email;

    @Column(name = "phone_number",length = 10,nullable = false)
    private String phoneNumber;

    @Column(name = "password",nullable = false)
    @Size(min = 8,max = 100,message="Password must be between 8 and 50 characters")
    private String password;

    @Column(name = "role", nullable = false)
    private UserRole role;
}

package org.VarunSeemar.digital_library.entities.input;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.VarunSeemar.digital_library.enums.MembershipPlan;

import java.time.Instant;

@Data
@Getter
@Setter
@Builder
public class MembershipInputEntity {
    @NotNull
    private MembershipPlan plan;
    @NotNull
    private long userId;
}

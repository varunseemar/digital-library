package org.VarunSeemar.digital_library.entities.input;

import lombok.*;
import org.VarunSeemar.digital_library.enums.MembershipStatus;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MembershipStatusUpdateEntity {
    private long userId;
    private MembershipStatus status;
}

package org.VarunSeemar.digital_library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.VarunSeemar.digital_library.enums.MembershipPlan;
import org.VarunSeemar.digital_library.enums.MembershipStatus;

import java.time.Instant;

@Builder
@Data
@With
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MembershipModel {
    private long id;
    private Instant startDate;
    private Instant endDate;
    private MembershipStatus status;
    @JsonIgnore
    private UserModel userModel;
    private MembershipPlan plan;

    @JsonProperty
    private long getUserId(){
        return this.userModel.getId();
    }
}

package org.VarunSeemar.digital_library.mappers.input;

import org.VarunSeemar.digital_library.entities.input.MembershipInputEntity;
import org.VarunSeemar.digital_library.enums.MembershipPlan;
import org.VarunSeemar.digital_library.enums.MembershipStatus;
import org.VarunSeemar.digital_library.model.MembershipModel;
import org.VarunSeemar.digital_library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class MembershipInputMapper {

    private final UserRepository userRepository;

    @Autowired
    public MembershipInputMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public MembershipModel mapToModel(MembershipInputEntity membershipInputEntity){

        Instant start = Instant.now();
        ZonedDateTime zonedStart = start.atZone(ZoneId.systemDefault());
        int endVariable = 0;

        MembershipPlan plan = membershipInputEntity.getPlan();
        switch(plan) {
            case ONE_YEAR -> endVariable = 12;
            case SIX_MONTHS -> endVariable = 6;
            case THREE_MONTHS -> endVariable = 3;
        }

        ZonedDateTime zonedEnd = zonedStart.plusMonths(endVariable);
        Instant end = zonedEnd.toInstant();

        return MembershipModel.builder()
                .startDate(start)
                .endDate(end)
                .userModel(this.userRepository.findUserById(membershipInputEntity.getUserId()))
                .plan(membershipInputEntity.getPlan())
                .status(MembershipStatus.ACTIVE)
                .build();
    }
}

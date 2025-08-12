package org.VarunSeemar.digital_library.service;

import org.VarunSeemar.digital_library.entities.output.MembershipOutputEntity;
import org.VarunSeemar.digital_library.entities.output.UserOutputEntity;
import org.VarunSeemar.digital_library.enums.MembershipPlan;
import org.VarunSeemar.digital_library.enums.MembershipStatus;
import org.VarunSeemar.digital_library.model.MembershipModel;
import org.VarunSeemar.digital_library.model.UserModel;
import org.VarunSeemar.digital_library.repository.MembershipRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class MembershipServiceTest {
    @Mock
    private MembershipRepository membershipRepository;

    @InjectMocks
    private MembershipService membershipService;

    @Test
    public void testAddMembership(){

        UserModel userModel = UserModel.builder()
                .id(1L)
                .firstName("Varun")
                .lastName("Seemar")
                .dob(Instant.now())
                .email("varun@abc.com")
                .phoneNumber("1234567890")
                .build();

        MembershipModel input = MembershipModel.builder()
                .id(2L)
                .status(MembershipStatus.ACTIVE)
                .plan(MembershipPlan.SIX_MONTHS)
                .startDate(Instant.now())
                .endDate(Instant.now())
                .userModel(userModel)
                .build();

        MembershipModel expectedResponse = MembershipModel.builder()
                .id(2L)
                .status(MembershipStatus.ACTIVE)
                .plan(MembershipPlan.SIX_MONTHS)
                .startDate(Instant.now())
                .endDate(Instant.now())
                .userModel(userModel)
                .build();

        UserOutputEntity userOutputEntity = UserOutputEntity.builder()
                .id(1L)
                .firstName("Varun")
                .lastName("Seemar")
                .dob(Instant.now())
                .email("varun@abc.com")
                .phoneNumber("1234567890")
                .password("1234567890")
                .build();

        MembershipOutputEntity membershipOutputEntity = MembershipOutputEntity.builder()
                .id(2L)
                .plan(MembershipPlan.SIX_MONTHS)
                .endDate(Instant.now())
                .status(MembershipStatus.ACTIVE)
                .startDate(Instant.now())
                .userOutputEntity(userOutputEntity)
                .build();

        Mockito.when(this.membershipRepository.addMembership(input)).thenReturn(expectedResponse);
        Mockito.when(this.membershipRepository.getNonExpiredMembershipForUser(userModel.getId())).thenReturn(Optional.empty());
        MembershipModel actualResponse = this.membershipService.addMembership(input);
        Assertions.assertEquals(expectedResponse,actualResponse);
    }
}

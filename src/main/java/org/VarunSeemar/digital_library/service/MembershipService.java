package org.VarunSeemar.digital_library.service;

import org.VarunSeemar.digital_library.entities.output.MembershipOutputEntity;
import org.VarunSeemar.digital_library.enums.MembershipStatus;
import org.VarunSeemar.digital_library.exceptions.MembershipException;
import org.VarunSeemar.digital_library.exceptions.NoActiveOrPausedMembershipException;
import org.VarunSeemar.digital_library.mappers.output.MembershipOutputMapper;
import org.VarunSeemar.digital_library.model.MembershipModel;
import org.VarunSeemar.digital_library.repository.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MembershipService {

    private final MembershipRepository membershipRepository;

    @Autowired
    public MembershipService(MembershipRepository membershipRepository, MembershipOutputMapper membershipOutputMapper) {
        this.membershipRepository = membershipRepository;
    }

    public MembershipModel addMembership(MembershipModel membershipModel){
        Optional<MembershipOutputEntity> membershipOutputEntity = this.membershipRepository.getNonExpiredMembershipForUser(membershipModel.getUserModel().getId());
        if(membershipOutputEntity.isPresent()){
            throw new MembershipException("Cant add membership as this user have a active or paused membership");
        }
        return this.membershipRepository.addMembership(membershipModel);
    }

    public MembershipModel getMembershipById(long id){
        return this.membershipRepository.getMembershipById(id);
    }

    public MembershipModel updateMembershipStatus(long userId, MembershipStatus membershipStatus){
        Optional<MembershipOutputEntity> optional = this.membershipRepository.getNonExpiredMembershipForUser(userId);
        if(optional.isEmpty()){
            throw new NoActiveOrPausedMembershipException("User Do Not Have Any Active Or Paused Membership.");
        }
        if(membershipStatus == optional.get().getStatus()){
            throw new MembershipException("Cant update " + membershipStatus + " status of " + optional.get().getStatus() + " Membership.");
        }
        return this.membershipRepository.updateStatus(optional.get().getId(), membershipStatus);
    }

}

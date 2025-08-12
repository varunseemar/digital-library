package org.VarunSeemar.digital_library.repository;

import lombok.extern.slf4j.Slf4j;
import org.VarunSeemar.digital_library.entities.output.MembershipOutputEntity;
import org.VarunSeemar.digital_library.enums.MembershipStatus;
import org.VarunSeemar.digital_library.exceptions.MembershipException;
import org.VarunSeemar.digital_library.mappers.output.MembershipOutputMapper;
import org.VarunSeemar.digital_library.model.MembershipModel;
import org.VarunSeemar.digital_library.repository.jpa.MembershipJPARepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class MembershipRepository {

    private final MembershipJPARepository membershipJPARepository;
    private final MembershipOutputMapper membershipOutputMapper;

    public MembershipRepository(MembershipJPARepository membershipJPARepository, MembershipOutputMapper membershipOutputMapper) {
        this.membershipJPARepository = membershipJPARepository;
        this.membershipOutputMapper = membershipOutputMapper;
    }

    public MembershipModel addMembership(MembershipModel membershipModel){
        MembershipOutputEntity membershipOutputEntity = this.membershipOutputMapper.mapFromModel(membershipModel);
        MembershipOutputEntity savedMembershipOutputEntity = this.membershipJPARepository.save(membershipOutputEntity);
        return this.membershipOutputMapper.mapToModel(savedMembershipOutputEntity);
    }

    public MembershipModel getMembershipById(long id){
        Optional<MembershipOutputEntity> membershipOutputEntity = this.membershipJPARepository.findById(id);
        return membershipOutputEntity.map(this.membershipOutputMapper::mapToModel).orElse(null);
    }

    public MembershipModel updateStatus(long membershipId,MembershipStatus membershipStatus){
        MembershipModel model = this.getMembershipById(membershipId);
        if(model == null){
            throw new MembershipException("Membership cant be found");
        }
        model.setStatus(membershipStatus);
        MembershipOutputEntity outputEntity = this.membershipOutputMapper.mapFromModel(model);
        MembershipOutputEntity savedOutputEntity = this.membershipJPARepository.save(outputEntity);
        log.info("Membership with ID : {} was successfully updated.",savedOutputEntity.getId());
        return this.membershipOutputMapper.mapToModel(savedOutputEntity);
    }

    public Optional<MembershipOutputEntity> getNonExpiredMembershipForUser(long id){
        return this.membershipJPARepository.findByUserOutputEntity_IdAndStatusNot(id, MembershipStatus.EXPIRED);
    }
}

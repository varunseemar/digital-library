package org.VarunSeemar.digital_library.adapter;

import org.VarunSeemar.digital_library.commons.CommonAdapter;
import org.VarunSeemar.digital_library.entities.input.MembershipInputEntity;
import org.VarunSeemar.digital_library.enums.MembershipStatus;
import org.VarunSeemar.digital_library.mappers.input.MembershipInputMapper;
import org.VarunSeemar.digital_library.model.MembershipModel;
import org.VarunSeemar.digital_library.service.MembershipService;
import org.VarunSeemar.digital_library.utils.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MembershipAdapter implements CommonAdapter<MembershipInputEntity, MembershipModel> {

    private final MembershipService membershipService;
    private final MembershipInputMapper membershipInputMapper;

    @Autowired
    public MembershipAdapter(MembershipService membershipService,MembershipInputMapper membershipInputMapper){
        this.membershipInputMapper = membershipInputMapper;
        this.membershipService = membershipService;
    }

    @Override
    public MembershipModel save(MembershipInputEntity membershipInputEntity) {
        return this.membershipService.addMembership(this.membershipInputMapper.mapToModel(membershipInputEntity));
    }

    @Override
    public MembershipModel update(MembershipInputEntity membershipInputEntity) {
        return TODO.todo();
    }

    @Override
    public List<MembershipModel> getAll() {
        return TODO.todo();
    }

    public MembershipModel getMembership(long id){
        return this.membershipService.getMembershipById(id);
    }

    public MembershipModel updateMembershipStatus(long userId, MembershipStatus membershipStatus){
        return this.membershipService.updateMembershipStatus(userId,membershipStatus);
    }
}

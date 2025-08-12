package org.VarunSeemar.digital_library.mappers.output;

import org.VarunSeemar.digital_library.entities.output.MembershipOutputEntity;
import org.VarunSeemar.digital_library.model.MembershipModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MembershipOutputMapper {

    private final UserOutputMapper userOutputMapper;

    @Autowired
    public MembershipOutputMapper(UserOutputMapper userOutputMapper) {
        this.userOutputMapper = userOutputMapper;
    }

    public MembershipModel mapToModel(MembershipOutputEntity membershipOutputEntity){
        return MembershipModel.builder()
                .id(membershipOutputEntity.getId())
                .startDate(membershipOutputEntity.getStartDate())
                .endDate(membershipOutputEntity.getEndDate())
                .status(membershipOutputEntity.getStatus())
                .userModel(this.userOutputMapper.mapToModel(membershipOutputEntity.getUserOutputEntity()))
                .plan(membershipOutputEntity.getPlan())
                .build();
    }

    public MembershipOutputEntity mapFromModel(MembershipModel membershipModel){
        return MembershipOutputEntity.builder()
                .id(membershipModel.getId())
                .startDate(membershipModel.getStartDate())
                .endDate(membershipModel.getEndDate())
                .status(membershipModel.getStatus())
                .userOutputEntity(this.userOutputMapper.mapFromModel(membershipModel.getUserModel()))
                .plan(membershipModel.getPlan())
                .build();
    }

}

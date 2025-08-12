package org.VarunSeemar.digital_library.repository.jpa;

import org.VarunSeemar.digital_library.entities.output.MembershipOutputEntity;
import org.VarunSeemar.digital_library.enums.MembershipStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MembershipJPARepository extends JpaRepository<MembershipOutputEntity,Long> {


    Optional<MembershipOutputEntity> findByUserOutputEntity_IdAndStatusNot(long id, MembershipStatus status);
}

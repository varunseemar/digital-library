package org.VarunSeemar.digital_library.repository.jpa;

import org.VarunSeemar.digital_library.entities.output.UserOutputEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJPARepository extends JpaRepository<UserOutputEntity, Long> {
    Optional<UserOutputEntity> findByEmail(String email);
}

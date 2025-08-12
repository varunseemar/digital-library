package org.VarunSeemar.digital_library.repository.jpa;

import org.VarunSeemar.digital_library.entities.output.BookOutputEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookJPARepository extends JpaRepository<BookOutputEntity,Long> {

}

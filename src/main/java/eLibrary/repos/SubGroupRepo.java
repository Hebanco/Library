package eLibrary.repos;

import eLibrary.domain.SubGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubGroupRepo extends JpaRepository<SubGroup, Long> {
    SubGroup findByName(String name);

}

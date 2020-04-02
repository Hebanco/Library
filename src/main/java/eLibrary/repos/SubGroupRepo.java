package eLibrary.repos;

import eLibrary.domain.LessonSubGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubGroupRepo extends JpaRepository<LessonSubGroup, Long> {
    LessonSubGroup findByName(String name);

}

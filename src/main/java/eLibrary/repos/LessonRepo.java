package eLibrary.repos;

import eLibrary.domain.Lesson;
import eLibrary.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface LessonRepo extends JpaRepository<Lesson, Long> {
    Lesson findByName(String name);
    Set<Lesson> findByTeacher(User teacher);
}

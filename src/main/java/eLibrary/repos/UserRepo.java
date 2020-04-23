package eLibrary.repos;

import eLibrary.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);

    List<User> findByUsernameLikeIgnoreCase(String username);

    @Query(value = "SELECT * from usr WHERE id IN (select user_id from user_role where roles = 'TEACHER')",
            nativeQuery = true)
    List<User> findTeacher();

    @Query(value = "SELECT * from usr WHERE id IN (select user_id from user_role where roles = 'TEACHER') AND username LIKE CONCAT('%',:username,'%')",
    nativeQuery = true)
    List<User> findTeacherByUsername(@Param("username") String username);

    User findByEmail(String email);

    User findByActivationCode(String code);
}

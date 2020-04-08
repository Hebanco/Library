package eLibrary.repos;

import eLibrary.domain.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepo extends CrudRepository<Book, Long> {

    List<Book> findByName(String name);

    @Query(value = "SELECT * FROM `book` ORDER BY RAND() LIMIT 5", nativeQuery = true)
    List<Book> findFiveByRandom();

    List<Book> findByNameLike(String name);
}

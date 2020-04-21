package eLibrary.repos;

import eLibrary.domain.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepo extends CrudRepository<Book, Long> {

    List<Book> findByName(String name);

    @Query(value = "SELECT * FROM `book` ORDER BY RAND() LIMIT 5", nativeQuery = true)
    List<Book> findFiveByRandom();

    @Query(value = "SELECT * from book WHERE name LIKE CONCAT('%',:name,'%')",
            nativeQuery = true)
    List<Book> findByNameLike(String name);
}

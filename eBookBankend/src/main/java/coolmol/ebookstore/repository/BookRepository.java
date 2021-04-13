package coolmol.ebookstore.repository;

import coolmol.ebookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("select b from Book b")
    List<Book> getBooks();

    @Query(value = "select * from Book b where b.name=name", nativeQuery = true)
    List<Book> getBookByName(@Param("name") String name);
}

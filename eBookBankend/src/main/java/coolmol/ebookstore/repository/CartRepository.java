package coolmol.ebookstore.repository;

import coolmol.ebookstore.entity.Book;
import coolmol.ebookstore.entity.Cart;
import coolmol.ebookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    List<Cart> findCartsByUser(User user);

    List<Cart> findCartsByBookAndUser(Book book, User user);

    List<Cart> findCartsByBook(Book book);
}

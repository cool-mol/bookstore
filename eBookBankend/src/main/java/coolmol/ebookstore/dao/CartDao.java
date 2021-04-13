package coolmol.ebookstore.dao;

import coolmol.ebookstore.entity.Book;
import coolmol.ebookstore.entity.Cart;
import coolmol.ebookstore.entity.User;

import java.util.List;

public interface CartDao {
    Cart addCartItem(Book book, User user, Integer amount);

    Cart deleteCartItem(Integer itemID);



    Cart changeItemAmount(Integer itemID, Integer amount);

    List<Cart> findCartItemsByUser(User user);

    void deleteCartItemsByBook(Book book);
}

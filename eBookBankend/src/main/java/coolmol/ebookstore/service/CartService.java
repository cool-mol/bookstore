package coolmol.ebookstore.service;

import coolmol.ebookstore.entity.Cart;
import coolmol.ebookstore.entity.Order;

import java.util.List;

public interface CartService {
    Cart addBookToCart(Integer bookID, Integer userID, Integer amount);

    List<Cart> findCartItemsByUser(Integer userID);

    Order addCartToOrder(Integer userID);

    Cart deleteCartItem(Integer userID, Integer itemID);

    Cart changeAmount(Integer itemID, Integer amount);

}


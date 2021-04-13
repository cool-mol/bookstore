package coolmol.ebookstore.daoimpl;

import coolmol.ebookstore.dao.CartDao;
import coolmol.ebookstore.entity.Book;
import coolmol.ebookstore.entity.Cart;
import coolmol.ebookstore.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import coolmol.ebookstore.repository.CartRepository;

import java.util.List;

@Repository
public class CartDaoImpl implements CartDao {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart addCartItem(Book book, User user, Integer amount){
        List<Cart> Carts = cartRepository.findCartsByBookAndUser(book, user);
        for (Cart c : Carts){
            int num = c.getAmount() + amount;
            c.setAmount(num);
            return cartRepository.saveAndFlush(c);
        }
        Cart cart = new Cart();
        cart.setAmount(amount);
        cart.setBook(book);
        cart.setUser(user);
        return cartRepository.saveAndFlush(cart);
    }

    @Override
    public Cart deleteCartItem(Integer itemID) {
        Cart cart = cartRepository.getOne(itemID);
        cartRepository.delete(cart);
        return cart;
    }

    @Override
    public Cart changeItemAmount(Integer itemID, Integer amount) {
        Cart cart = cartRepository.getOne(itemID);
        cart.setAmount(amount);
        return cartRepository.saveAndFlush(cart);
    }

    @Override
    public List<Cart> findCartItemsByUser(User user) {
        return cartRepository.findCartsByUser(user);
    }

    @Override
    public void deleteCartItemsByBook(Book book) {
        List<Cart> carts = cartRepository.findCartsByBook(book);
        for(Cart cart : carts){
            cartRepository.delete(cart);
        }
    }

}

package coolmol.ebookstore.serviceimpl;

import coolmol.ebookstore.dao.BookDao;
import coolmol.ebookstore.dao.CartDao;
import coolmol.ebookstore.dao.OrderDao;
import coolmol.ebookstore.dao.UserDao;
import coolmol.ebookstore.entity.Book;
import coolmol.ebookstore.entity.Cart;
import coolmol.ebookstore.entity.Order;
import coolmol.ebookstore.entity.User;
import coolmol.ebookstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    OrderDao orderDao;
    @Autowired
    CartDao cartDao;
    @Autowired
    UserDao userDao;
    @Autowired
    BookDao bookDao;

    @Override
    public Cart addBookToCart(Integer bookID, Integer userID, Integer amount) {
        Book book = bookDao.findOne(bookID);
        User user = userDao.findOne(userID);
        return cartDao.addCartItem(book, user, amount);
    }

    @Override
    public List<Cart> findCartItemsByUser(Integer userID) {
        System.out.println("I'm Service");
        User user = userDao.findOne(userID);
        List<Cart> carts = cartDao.findCartItemsByUser(user);
        for(Cart cart : carts){
            cart.setBook(bookDao.findOne(cart.getBook().getBookId()));
        }
        return carts;
    }

    @Override
    public Order addCartToOrder(Integer userID) {
        System.out.println("I'm Service in addCartToOrder");
        User user = userDao.findOne(userID);
        List<Cart> Carts = cartDao.findCartItemsByUser(user);
        for(Cart cart : Carts){
            Book book = cart.getBook();
            bookDao.changeInventory(book.getBookId(), cart.getAmount());
            deleteCartItem(userID, cart.getId());
        }
        return orderDao.addCartToOrder(user, Carts);
    }

    @Override
    public Cart deleteCartItem(Integer userID, Integer itemID) {
        return cartDao.deleteCartItem(itemID);
    }

    @Override
    public Cart changeAmount(Integer itemID, Integer amount) {
        return cartDao.changeItemAmount(itemID, amount);
    }
}

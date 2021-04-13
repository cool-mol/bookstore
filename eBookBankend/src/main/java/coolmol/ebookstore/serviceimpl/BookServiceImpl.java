package coolmol.ebookstore.serviceimpl;

import coolmol.ebookstore.dao.BookDao;
import coolmol.ebookstore.dao.CartDao;
import coolmol.ebookstore.dao.OrderDao;
import coolmol.ebookstore.entity.Book;
import coolmol.ebookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private CartDao cartDao;

    @Override
    public List<Book> getBooks() {
        return bookDao.getBooks();
    }

    @Override
    public Book findBookById(Integer id) {
        return bookDao.findOne(id);
    }

    @Override
    public Book changeOnesInfo(Integer bookId, String imageBase64, String name, String author, String isbn, Integer inventory, Integer price, String description) {
        return bookDao.changeOnesInfo(bookId, imageBase64, name, author, isbn, inventory, price, description);
    }

    @Override
    public Book deleteOneBook(Integer bookId) {
        Book book = bookDao.findOne(bookId);
        orderDao.deleteOrderItemsByBook(book);
        cartDao.deleteCartItemsByBook(book);
        return bookDao.deleteOneBook(bookId);
    }

    @Override
    public Book insertNewBook(String imageBase64, String name, String author, String type, String isbn, Integer inventory, Integer price, String description) {
        return bookDao.insertNewBook(imageBase64, name, author, type, isbn, inventory, price, description);
    }
}

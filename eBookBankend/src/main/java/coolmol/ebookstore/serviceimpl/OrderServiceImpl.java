package coolmol.ebookstore.serviceimpl;

import coolmol.ebookstore.dao.BookDao;
import coolmol.ebookstore.dao.OrderDao;
import coolmol.ebookstore.dao.UserDao;
import coolmol.ebookstore.entity.Order;
import coolmol.ebookstore.entity.OrderItem;
import coolmol.ebookstore.entity.User;
import coolmol.ebookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private BookDao bookDao;

    @Override
    public List<Order> findOnesOrder(Integer userID) {
        User user = userDao.findOne(userID);
        return orderDao.findByUserID(user);
    }

    @Override
    public List<OrderItem> findOrderItemsByOrderID(Integer orderID) {
        System.out.println("I'm Service");
        List<OrderItem> orderItems = orderDao.findOrderItemsByOrder(orderDao.findOrderByOrderID(orderID));
        for(OrderItem o : orderItems){
            o.setBook(bookDao.findOne(o.getBook().getBookId()));
        }
        return orderItems;
    }

    @Override
    public List<Order> findAllOrders() {
        return orderDao.findAllOrders();
    }

}

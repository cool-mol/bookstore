package coolmol.ebookstore.repository;

import coolmol.ebookstore.entity.Order;
import coolmol.ebookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findOrdersByUser(User user);

    List<Order> findOrdersByDateBetween(Date date1, Date date2);

    List<Order> findOrdersByDateBetweenAndUser(Date date1, Date date2, User user);
}

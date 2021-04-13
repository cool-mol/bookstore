package coolmol.ebookstore.repository;

import coolmol.ebookstore.entity.Book;
import coolmol.ebookstore.entity.Order;
import coolmol.ebookstore.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    List<OrderItem> findOrderItemsByOrder(Order order);

    List<OrderItem> findOrderItemsByBook(Book book);
}

package coolmol.ebookstore.service;

import coolmol.ebookstore.entity.Order;
import coolmol.ebookstore.entity.OrderItem;

import java.util.List;

public interface OrderService {

    List<Order> findOnesOrder(Integer userID);

    List<OrderItem> findOrderItemsByOrderID(Integer orderID);

    List<Order> findAllOrders();

}

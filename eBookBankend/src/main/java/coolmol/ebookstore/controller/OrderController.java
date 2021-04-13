package coolmol.ebookstore.controller;

import coolmol.ebookstore.entity.Order;
import coolmol.ebookstore.entity.OrderItem;
import coolmol.ebookstore.service.CartService;
import coolmol.ebookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    CartService cartService;

    @GetMapping("/findOnesOrder/{userID}")
    public List<Order> findOnesOrder(@PathVariable Integer userID) {
        return orderService.findOnesOrder(userID);
    }

    @GetMapping("/findOrderItems/{orderID}")
    public List<OrderItem> findOrderItemsByOrderID(@PathVariable Integer orderID) {
        System.out.println("findOrderItems");
        return orderService.findOrderItemsByOrderID(orderID);
    }

    @GetMapping("/findAllOrders")
    public List<Order> findAllOrders(){
        System.out.println("findAllOrders");
        return orderService.findAllOrders();
    }
}

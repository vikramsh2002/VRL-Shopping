package vrl.VRL_EShopping.Service.Interfaces;

import vrl.VRL_EShopping.Model.OrderEntity.Order;
import vrl.VRL_EShopping.Model.OrderEntity.Orders;

import java.util.List;

public interface OrderService {
    String punchOrder(Order o1,String user);

    String updateOrderStatus(String user,String status,int OrderId);

    String cancelOrder(int orderId,String user);

    List<Order> ListOrdersByUser(String username);
    Order searchByOrderId(int orderId,String username);

    int GenerateOrderId(String username);

}

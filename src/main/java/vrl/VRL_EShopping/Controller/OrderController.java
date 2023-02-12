package vrl.VRL_EShopping.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vrl.VRL_EShopping.Model.Carts.Cart;
import vrl.VRL_EShopping.Model.OrderEntity.Order;
import vrl.VRL_EShopping.Model.OrderEntity.Request.RequestOrderIdAndUser;
import vrl.VRL_EShopping.Model.OrderEntity.Request.RequestAddOrderByUser;
import vrl.VRL_EShopping.Model.OrderEntity.Request.RequestForListingOrders;
import vrl.VRL_EShopping.Model.OrderEntity.Request.StatusRequest;
import vrl.VRL_EShopping.Repo.CartRepo;
import vrl.VRL_EShopping.Service.Interfaces.OrderService;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    CartRepo cartRepo;
    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/PlaceOrder", method = RequestMethod.POST)
    String placeOrder(@RequestBody RequestAddOrderByUser requestAddOrderByUser) {
        Order o1 = new Order();
        if (cartRepo.existsById(requestAddOrderByUser.getUser())) {
            o1.setOrderId(orderService.GenerateOrderId(requestAddOrderByUser.getUser()));
            Cart items = cartRepo.findById(requestAddOrderByUser.getUser()).get();

            // Preparing order data
            o1.setProductToOrder(items.getCartitems());
            o1.setOrderStatus("Order Placed");
            o1.setDeliveryAddress(requestAddOrderByUser.getDeliveryAddress());
            o1.setDeliveryCharge(50);
            o1.setTotalpayableamout(items.getTotalprice() + o1.getDeliveryCharge());

            return orderService.punchOrder(o1, requestAddOrderByUser.getUser());
        } else {
            return "Cart Is Empty Please Add Some Items..";
        }

    }

@RequestMapping(value = "/CancelOrder",method = RequestMethod.POST)
String cancel(@RequestBody RequestOrderIdAndUser request){
        return orderService.cancelOrder(request.getOrderId(),request.getUser());

}


@RequestMapping(value = "/ChangeOrderStatus",method = RequestMethod.POST)
 String updateStatus(@RequestBody StatusRequest request){
        return orderService.updateOrderStatus(request.getUser(),request.getStatus(),request.getOrderId());
}

@RequestMapping(value = "/ListAllTheOrders",method = RequestMethod.POST)
    List<Order> ShowOrders(@RequestBody RequestForListingOrders request){
        return orderService.ListOrdersByUser(request.getUser());
}


@RequestMapping(value = "/SearchByOrderId",method = RequestMethod.POST)
    Order searchByOrderId(@RequestBody RequestOrderIdAndUser requestOrderIdAndUser){
        return orderService.searchByOrderId(requestOrderIdAndUser.getOrderId(),requestOrderIdAndUser.getUser());
}








}

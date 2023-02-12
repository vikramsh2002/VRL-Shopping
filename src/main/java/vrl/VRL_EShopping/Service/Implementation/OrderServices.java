package vrl.VRL_EShopping.Service.Implementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vrl.VRL_EShopping.Model.OrderEntity.Order;
import vrl.VRL_EShopping.Model.OrderEntity.Orders;
import vrl.VRL_EShopping.Repo.CartRepo;
import vrl.VRL_EShopping.Repo.OrderRepo;
import vrl.VRL_EShopping.Repo.UserRepo;
import vrl.VRL_EShopping.Service.Interfaces.OrderService;

import java.util.ArrayList;
import java.util.List;
@Service
public class OrderServices implements OrderService{
    @Autowired
    OrderRepo orderRepo;

    @Autowired
    UserRepo userRepo;


    // Unique OrdeId
    @Override
    public int GenerateOrderId(String username) {
        if (orderRepo.existsById(username)) {
            Orders userOrders = orderRepo.findById(username).get();
            if (userOrders.getOrders().isEmpty()) {
                return 10001;
            } else {
                int mx = userOrders.getOrders().stream().mapToInt(e -> e.getOrderId()).max().getAsInt();
                return mx + 1;
            }

        }
        else{
            return 100;
        }
    }
    @Override
    public String punchOrder(Order o1,String user) {
        if(userRepo.existsById(user)){
            if(orderRepo.existsById(user)){
                    // Already prev orders // add new order
                    Orders userOrders= orderRepo.findById(user).get();
                    List<Order> tmp = userOrders.getOrders();
                    tmp.add(o1);

                    // Locally update
                    userOrders.setOrders(tmp);

                    // Update in Mongo
                    orderRepo.save(userOrders);
                return "Successfully Placed The Order..";
            }
            else{

                Orders firstorder=new Orders();


                List<Order> tmp = new ArrayList<Order>();
                tmp.add(o1);

                firstorder.setUsername(user);
                firstorder.setOrders(tmp);

                // Save mongo
                orderRepo.save(firstorder);
                return "Successfully Placed The Order..";
            }

        }
        else{
            return "User Doesn't Exist ..";
        }
    }

    @Override
    public String updateOrderStatus(String user,String status,int OrderId) {
        /*
        * Status
        *   1. Order Placed
        *   2. Order Dispatched
        *   3. Delivered
        * */
        if(userRepo.existsById(user)){
            if(orderRepo.existsById(user)){
                // Iterate orders and find the order id
                int index=-1;
                Orders userOrders= orderRepo.findById(user).get();
                for(Order order:userOrders.getOrders())
                {
                    index+=1;
                    if(order.getOrderId() == OrderId){

                        break;
                    }
                }
                if(index!=-1)
                {
                     // Status update

                    // verify status
                    if(status.equalsIgnoreCase("Order Placed") || status.equalsIgnoreCase("Order Dispatched") || status.equalsIgnoreCase("Order Delivered") ){
                        // Update
                        List<Order> tmp = userOrders.getOrders();

                        Order od = tmp.get(index);
                        od.setOrderStatus(status);

                        // Locally Update
                        tmp.set(index,od);
                        userOrders.setOrders(tmp);

                        // Update Mongo
                        orderRepo.save(userOrders);
                        return "Successfully Updated the Order Status to "+status;

                    }
                    else{
                        return "Invalid Status passed( i.e., it must be from these 3 that are \nOrder Placed\n" +
                                "Order Dispatched\n" +
                                "Order Delivered";
                    }

                }
                else
                {
                    return "Check The Order Id Once..";
                }


            }
            else{
                return "No Order Placed Yet..";
            }

        }
        else{
            return "User Doesn't Exist ..";
        }


    }

    @Override
    public String cancelOrder(int orderId,String user) {
        if(userRepo.existsById(user)){
            if(orderRepo.existsById(user))
            {
                Orders userOrders = orderRepo.findById(user).get();
                int index=-1;
                for(Order order:userOrders.getOrders()){
                    index+=1;
                    if(order.getOrderId()==orderId){
                        break;
                    }
                }
                if(index!=-1)
                {
                    // Take list item remove
                    List<Order> tmp = userOrders.getOrders();
                    if(tmp.get(index).getOrderStatus().equalsIgnoreCase("Order Delivered"))
                    {
                        return "Cannot Cancel The Delivered Order..";
                    }
                    tmp.remove(index);

                    // Locally Updated
                    userOrders.setOrders(tmp);

                    // Mongo
                    orderRepo.save(userOrders);
                    return "Order Cancelled..";
                }
                else{
                    return "Please Check the Order Id..";
                }
            }
            else {
                return "No Order Placed Yet..";
            }

        }
        else{
            return "User Doesn't Exist ..";
        }

    }

    @Override
    public List<Order> ListOrdersByUser(String username) {
        if(userRepo.existsById(username)){
            if(orderRepo.existsById(username)){
                return orderRepo.findById(username).get().getOrders();
            }
            else{
                return  null;
            }

        }
        else{
            return null;
        }
    }


    @Override
    public Order searchByOrderId(int orderId,String user) {
        if(userRepo.existsById(user)){
            if(orderRepo.existsById(user)){
                int index=-1;
                Orders userOrders =orderRepo.findById(user).get();
                for(Order order:userOrders.getOrders())
                {
                    index+=1;
                    if(order.getOrderId()==orderId){
                        return order;
                    }
                }
                return null;
            }
            else{
                return null;
            }

        }
        return null;
    }
}

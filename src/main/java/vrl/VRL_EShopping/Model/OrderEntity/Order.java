package vrl.VRL_EShopping.Model.OrderEntity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import vrl.VRL_EShopping.Model.Carts.CartItem;
import vrl.VRL_EShopping.Model.Products.UniqueProd;

import java.util.HashMap;
import java.util.List;
public @Data class Order {
    @Id
    private int OrderId;
    List<CartItem> productToOrder; // hashmap of product id as key and qty as value
    private String orderStatus;  // successfully delivered or dispatch or iniatited
    private double deliveryCharge;
    private double totalpayableamout;
    private String deliveryAddress;
}

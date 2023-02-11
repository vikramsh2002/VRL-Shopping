package vrl.VRL_EShopping.Model.Carts;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;
public @Data class Cart {

    @Id
    String username;
    List<CartItem> cartitems;
    double totalprice;



}

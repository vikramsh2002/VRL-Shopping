package vrl.VRL_EShopping.Model.Carts;

import lombok.Data;
import org.springframework.data.annotation.Id;

import vrl.VRL_EShopping.Model.Products.UniqueProd;

public @Data class CartItem {  // @Data : add getter & setters

    @Id
    UniqueProd id;
    private int quantity;


}

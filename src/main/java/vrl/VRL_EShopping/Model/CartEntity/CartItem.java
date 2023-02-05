package vrl.VRL_EShopping.Model.CartEntity;

import lombok.Data;

public @Data class CartItem {  // @Data : add getter & setters
    private int cartItemid;
    private int quantity;

    private int prodId;
}

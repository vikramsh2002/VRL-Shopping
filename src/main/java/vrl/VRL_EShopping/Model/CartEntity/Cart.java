package vrl.VRL_EShopping.Model.CartEntity;
import lombok.Data;

import java.util.List;
public @Data class Cart {
    List<CartItem> items;
    double totalprice;
    int userId;
}

package vrl.VRL_EShopping.Model.Carts.Request;

import lombok.Data;
import vrl.VRL_EShopping.Model.Carts.CartItem;

public@Data class addItemRequest {
    String username;
    CartItem cartitem;
}

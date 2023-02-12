package vrl.VRL_EShopping.Model.Carts.Request;

import lombok.Data;
import vrl.VRL_EShopping.Model.Products.UniqueProd;

public @Data class RequestUserQtyProduct {
    String username;
    UniqueProd prodId;
    int quantity;
}

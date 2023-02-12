package vrl.VRL_EShopping.Model.Carts.Request;

import lombok.Data;
import vrl.VRL_EShopping.Model.Products.UniqueProd;

public @Data class RequestByUserAndProduct {
    String username;
    UniqueProd prodId; // Note it is Class that has Product name and Brand
}

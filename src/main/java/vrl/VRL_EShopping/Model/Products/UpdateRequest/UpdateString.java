package vrl.VRL_EShopping.Model.Products.UpdateRequest;

import lombok.Data;
import vrl.VRL_EShopping.Model.Products.UniqueProd;

public @Data class UpdateString {
    UniqueProd prodId;
    String data;
}

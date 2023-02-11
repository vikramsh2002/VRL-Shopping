package vrl.VRL_EShopping.Model.UpdateRequest;

import lombok.Data;
import vrl.VRL_EShopping.Model.Products.UniqueProd;

import java.util.Map;

public @Data class UpdateMap {
    UniqueProd prodId;
    Map<String,String> data;
}

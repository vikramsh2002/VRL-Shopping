package vrl.VRL_EShopping.Model.OrderEntity;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
public @Data class Order {
    HashMap<Integer,Integer> productToOrder; // hashmap of product id as key and qty as value
    private boolean orderStatus;  // successfully delivered or not
    private double payableamout;
}

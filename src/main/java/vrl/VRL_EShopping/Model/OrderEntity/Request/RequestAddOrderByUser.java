package vrl.VRL_EShopping.Model.OrderEntity.Request;

import lombok.Data;
import vrl.VRL_EShopping.Model.OrderEntity.Order;

public @Data class RequestAddOrderByUser {
    String user;
    String deliveryAddress;

}

package vrl.VRL_EShopping.Model.OrderEntity.Request;

import lombok.Data;

public @Data class StatusRequest {
    String user;
    String status;
    int orderId;
}

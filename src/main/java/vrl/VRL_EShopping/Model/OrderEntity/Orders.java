package vrl.VRL_EShopping.Model.OrderEntity;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;
public @Data class Orders {
    private List<Order> orders;
    private int userId;
    @Id
    private int OrderId;
}

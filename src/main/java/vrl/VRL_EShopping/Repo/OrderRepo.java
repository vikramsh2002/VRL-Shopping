package vrl.VRL_EShopping.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import vrl.VRL_EShopping.Model.OrderEntity.Orders;

public interface OrderRepo extends MongoRepository<Orders,String> {
}

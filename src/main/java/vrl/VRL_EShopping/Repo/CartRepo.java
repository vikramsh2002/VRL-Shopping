package vrl.VRL_EShopping.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import vrl.VRL_EShopping.Model.Carts.Cart;

public interface CartRepo extends MongoRepository<Cart,String> {
}

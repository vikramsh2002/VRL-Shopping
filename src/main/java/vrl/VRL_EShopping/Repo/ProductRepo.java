package vrl.VRL_EShopping.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import vrl.VRL_EShopping.Model.Products.Product;

public interface ProductRepo extends MongoRepository<Product,String> {
}

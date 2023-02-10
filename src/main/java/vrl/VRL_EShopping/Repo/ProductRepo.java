package vrl.VRL_EShopping.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import vrl.VRL_EShopping.Model.Products.ProductData;
import vrl.VRL_EShopping.Model.Products.UniqueProd;

public interface ProductRepo extends MongoRepository<ProductData, UniqueProd> {
}

package vrl.VRL_EShopping.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import vrl.VRL_EShopping.Model.Users;
public interface UserRepo extends MongoRepository<Users,String> {

}

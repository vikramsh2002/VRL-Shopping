package vrl.VRL_EShopping.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;
import vrl.VRL_EShopping.Model.Login.Users;

@Repository
public interface UserRepo extends MongoRepository<Users,String>{

}

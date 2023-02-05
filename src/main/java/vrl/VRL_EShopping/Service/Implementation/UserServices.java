package vrl.VRL_EShopping.Service.Implementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vrl.VRL_EShopping.Model.Users;
import vrl.VRL_EShopping.Repo.UserRepo;
import vrl.VRL_EShopping.Service.Interfaces.UserService;
@Service
public class UserServices implements  UserService{

    @Autowired
    private UserRepo rp1;
    public Users signUp(Users u1){
        // new user add
        String pass=u1.getPassword();
        int pas=passHash(pass);
        u1.setPassword(""+pas);
        return rp1.save(u1);

    }
    public String signIn(Users u1){
        return null;
    }

    private int passHash( String pass){
          return 0;
    }

}

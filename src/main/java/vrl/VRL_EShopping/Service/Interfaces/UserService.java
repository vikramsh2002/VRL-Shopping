package vrl.VRL_EShopping.Service.Interfaces;
import vrl.VRL_EShopping.Model.Users;
public interface UserService {
    Users signUp(Users u1); // Create new user
    String signIn(Users u1); //
    void forgotPassword(Users u1);

}

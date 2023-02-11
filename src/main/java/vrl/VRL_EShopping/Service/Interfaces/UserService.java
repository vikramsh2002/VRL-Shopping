package vrl.VRL_EShopping.Service.Interfaces;
import vrl.VRL_EShopping.Model.Login.Forgot;
import vrl.VRL_EShopping.Model.Login.LoginUser;
import vrl.VRL_EShopping.Model.Login.Users;
public interface UserService {
    String signUp(Users u1); // Create new user
    String signIn(LoginUser u1); //
    String forgotPassword(Forgot u1);


}

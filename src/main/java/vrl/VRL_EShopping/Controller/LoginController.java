package vrl.VRL_EShopping.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vrl.VRL_EShopping.Model.Login.Forgot;
import vrl.VRL_EShopping.Model.Login.LoginUser;
import vrl.VRL_EShopping.Model.Login.Users;
import vrl.VRL_EShopping.Service.Implementation.UserServices;


@RestController
public class LoginController {

    @Autowired
    private UserServices us;
    @RequestMapping(value="/register",method = RequestMethod.POST)
    String addNew(@RequestBody Users s1){
        return us.signUp(s1);
    }

   @RequestMapping(value="/login",method = RequestMethod.POST)
    String login(@RequestBody  LoginUser u1) {
       // Verify user
       return us.signIn(u1);
   }

   @RequestMapping(value="/forgotPassword",method = RequestMethod.POST)
   String forgot(@RequestBody Forgot user){
        return us.forgotPassword(user);
   }

}

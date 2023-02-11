package vrl.VRL_EShopping.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;
import vrl.VRL_EShopping.Model.Login.Forgot;
import vrl.VRL_EShopping.Model.Login.LoginUser;
import vrl.VRL_EShopping.Model.Login.Users;
import vrl.VRL_EShopping.Service.Implementation.UserServices;
import vrl.VRL_EShopping.Service.Interfaces.UserService;


@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/register",method = RequestMethod.POST)
    String addNew(@RequestBody Users s1){
        return userService.signUp(s1);
    }

   @RequestMapping(value="/login",method = RequestMethod.POST)
    String login(@RequestBody  LoginUser u1) {
       // Verify user
       return userService.signIn(u1);
   }

   @RequestMapping(value="/forgotPassword",method = RequestMethod.POST)
   String forgot(@RequestBody Forgot user){
        return userService.forgotPassword(user);
   }

}

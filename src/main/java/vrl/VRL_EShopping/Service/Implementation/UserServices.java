package vrl.VRL_EShopping.Service.Implementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vrl.VRL_EShopping.Model.Login.Forgot;
import vrl.VRL_EShopping.Model.Login.LoginUser;
import vrl.VRL_EShopping.Repo.UserRepo;
import vrl.VRL_EShopping.Security.Classdef.EncryDecrypt;
import vrl.VRL_EShopping.Model.Login.Users;
import vrl.VRL_EShopping.Service.Interfaces.UserService;

import java.util.regex.Pattern;
@Service
public class UserServices implements  UserService{

    @Autowired
    private UserRepo rp1;

    public String signUp(Users u1){
        // new user add
        String pass=u1.getPassword();
        if (ValidPass(pass)){
            // Encrypt it
            String enpass = passEncrypt(pass);
            String email = passEncrypt(u1.getEmail());
            u1.setPassword(enpass);
            u1.setEmail(email);
            try {
                rp1.save(u1);
                return "Successfully Registered....";
            }
            catch (Exception e){
                return "Not Able to Register Due to email already exist \n Error: "+e.getMessage();
            }
        }
        return "Registration Failer Bad Password It must contains 8-15 char and at least 1 lower,1uppercase,1Number and\n1soecial symbol ";
    }


    public String signIn(LoginUser u){
        Users u1;

        try {
             u1= rp1.findById(u.getUsername()).get();
        }
        catch(Exception e){
            return "Incorrect Username";
        }
        if (u1 ==null){
            return "Incorrect Username";
        }
        EncryDecrypt ed = new EncryDecrypt();

        if(u1.getPassword().equals(ed.encrypt(u.getPassword()))){
                return "Sucessfully Login";
            }
            else{
                return "Incorrect Password";
            }



    }


    private boolean ValidPass(String pass){
        String exp = "^(?=.*[0-9])"     // at least number
                + "(?=.*[a-z])(?=.*[A-Z])"  // at least uper and lower 1
                + "(?=.*[@#$%^&+=])"     // at least sp
                + "(?=\\S+$).{8,15}$";   // 8-15

        Pattern p = Pattern.compile(exp);
        if (pass != null) {
            if(p.matcher(pass).matches())
            {
                //System.out.println(" Valid Password ");
                return true;
            }
            else{
                //System.out.println(" Invalid Password ");
                return false;
            }

        }
        return false;
    }

    private String passEncrypt(String pass){
       EncryDecrypt ed = new EncryDecrypt();
        return ed.encrypt(pass);
    }


    //  Updating
    public String forgotPassword(Forgot user){
        // gen otp nd verify email
        Users u1;
        try {
            u1 = rp1.findById(user.getUsername()).get();
        }
        catch(Exception e){
            return "Username Doesn't Exist";
        }
        if(u1 ==null){
            return "Username Doesn't Exist";
        }
        else{
            // Authorization need email to verify
            EncryDecrypt ed = new EncryDecrypt();

            if (ed.encrypt(user.getEmail()).equals(u1.getEmail()))
            {
                // Check the validity
                if(ValidPass(user.getNewpass())){
                    // update
                    u1.setPassword(ed.encrypt(user.getNewpass()));
                    rp1.save(u1);
                    return "Successfully Updated..";
                }
                else{
                    return "Invalid Password format must contains at least one uppercase,"+
                            "one numeric, one special symbol and Length of password must be "+
                            "In Between 8 to 15 Characters";
                }
            }
            else{
                return "Invalid Email!! So, Not Authorize to Update..";
            }

        }


    }



}

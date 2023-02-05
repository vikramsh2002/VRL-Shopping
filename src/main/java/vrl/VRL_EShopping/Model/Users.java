package vrl.VRL_EShopping.Model;

import org.springframework.data.annotation.Id;

public class Users {
    @Id
    private String userName;
    @Id
    private int custmerId;
    @Id
    private String email;
    private String FullName;

    private String password;

}

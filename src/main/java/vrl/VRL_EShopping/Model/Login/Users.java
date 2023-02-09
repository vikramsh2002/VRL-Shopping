package vrl.VRL_EShopping.Model.Login;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Random;
import java.util.regex.Pattern;
@Document
public  @Data class Users {
    @Id
    private String userName;

    @Indexed(unique = true)   // greate unqiue value constriant
    private String email;
    private String fullName;

    private String password;


}

package vrl.VRL_EShopping.Model.Products;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.HashMap;

public @Data class Product {
    @Id
    private  int productId=0;
    private ProdData d1;

}

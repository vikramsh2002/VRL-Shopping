package vrl.VRL_EShopping.Model.Products;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Map;

public @Data class ProductData {
    @Id
    private UniqueProd id;

    private double productPrice;
    private Map<String,String> review; // username,review
    private double averageRating;
    private String imgurl;
    private String category;
    private double discount;
    private boolean outOfStock;
}

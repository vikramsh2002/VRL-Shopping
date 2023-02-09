package vrl.VRL_EShopping.Model.Products;

import lombok.Data;

import java.util.HashMap;

public @Data class ProdData {
    private String productName;
    private double productPrice;
    private HashMap<Integer,String> review;
    private double averageRating;
    private String imgurl;
    private String category;
    private double discount;
    private boolean outOfStock;




}

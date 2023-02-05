package vrl.VRL_EShopping.Model;

import org.springframework.data.annotation.Id;

import java.util.HashMap;

public class Product {
    @Id
    private int productId;

    private String productName;
    private double productPrice;
    private HashMap<Integer,String> review;
    private double averageRating;
    private String imgurl;
    private String category;
    private double discount;
    private boolean outOfStock;



}

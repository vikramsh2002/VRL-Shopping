package vrl.VRL_EShopping.Service.Interfaces;



import vrl.VRL_EShopping.Model.Products.ProductData;
import vrl.VRL_EShopping.Model.Products.UniqueProd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ProductService {
    // Product
    ProductData addProduct(ProductData p1);


    // Product Fetching and Searching
    List<ProductData> getAllProductList();
    ProductData searchProductById(UniqueProd id);
    List<ProductData> searchProductPriceRange(double low,double high);
    List<ProductData> searchProductAboveRating(double rating);
    List<ProductData> searchProductByCategory(String category);
    List<ProductData> searchProductByDiscount(double discount);


    // Update
    String updatePrice(UniqueProd p1,double price);
    String updateDiscount(UniqueProd p1, double discount);
    String updateReviewPid(UniqueProd p1, Map<String,String> userReview);

    String updateAvgRating(UniqueProd p1, double newRating);

    // Update the product status
    String updateOrderStocks(UniqueProd p1,boolean oos);

    // Update Category
    String updateCategory(UniqueProd p1,String Category);
    String updateImgUrl(UniqueProd p1,String img);



}

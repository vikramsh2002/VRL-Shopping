package vrl.VRL_EShopping.Service.Interfaces;



import vrl.VRL_EShopping.Model.Products.ProductData;
import vrl.VRL_EShopping.Model.Products.UniqueProd;

import java.util.HashMap;
import java.util.List;
public interface ProductService {
    // Product
    ProductData addProduct(ProductData p1);
    List<ProductData> addProducts(List<ProductData>prods);


    // Product Fetching
    List<ProductService> getAllProductList();
    ProductData searchProductById(UniqueProd id);
    List<ProductService> searchProductByPrice(String productname, double price);

    // Update
    void updatePrice(double price,int pid);
    void updateDiscount(double discount,int pid);
    void updateReviewPid(int pid, HashMap<Integer,String> userReview);

    void updateAvgRating(int pid, double newRating);

    // Update the product status
    void updateOrderStocks(int pid,boolean oos);



}

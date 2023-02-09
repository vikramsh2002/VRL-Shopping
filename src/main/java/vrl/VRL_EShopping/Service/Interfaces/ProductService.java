package vrl.VRL_EShopping.Service.Interfaces;
import vrl.VRL_EShopping.Model.Products.ProdData;
import vrl.VRL_EShopping.Model.Products.Product;

import java.util.HashMap;
import java.util.List;
public interface ProductService {
    // Product
    Product addProduct(ProdData p1);
    List<Product> addProducts(List<ProdData>prods);


    // Product Fetching
    List<ProductService> getAllProductList();
    List<ProductService> searchProductById(int pid);
    List<ProductService> searchProductByPrice(String productname, double price);

    // Update
    void updatePrice(double price,int pid);
    void updateDiscount(double discount,int pid);
    void updateReviewPid(int pid, HashMap<Integer,String> userReview);

    void updateAvgRating(int pid, double newRating);

    // Update the product status
    void updateOrderStocks(int pid,boolean oos);



}

package vrl.VRL_EShopping.Service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vrl.VRL_EShopping.Model.Products.ProductData;
import vrl.VRL_EShopping.Model.Products.UniqueProd;
import vrl.VRL_EShopping.Repo.ProductRepo;
import vrl.VRL_EShopping.Service.Interfaces.ProductService;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProdService implements ProductService {
    @Autowired
    ProductRepo prp1;
    @Override
    public ProductData addProduct(ProductData p1) {
        return prp1.save(p1);
    }

    @Override
    public List<ProductData> addProducts(List<ProductData> prods) {
        return null;
    }

    @Override
    public List<ProductService> getAllProductList() {

        return null;
    }

    @Override
    public ProductData searchProductById(UniqueProd id) {

        try{
            return prp1.findById(id).get();
        }
        catch(NoSuchElementException e){
            return null;
        }

    }

    @Override
    public List<ProductService> searchProductByPrice(String productname, double price) {
        return null;
    }

    @Override
    public void updatePrice(double price, int pid) {

    }

    @Override
    public void updateDiscount(double discount, int pid) {

    }

    @Override
    public void updateReviewPid(int pid, HashMap<Integer, String> userReview) {

    }

    @Override
    public void updateAvgRating(int pid, double newRating) {

    }

    @Override
    public void updateOrderStocks(int pid, boolean oos) {

    }
}

package vrl.VRL_EShopping.Service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vrl.VRL_EShopping.Model.Products.ProdData;
import vrl.VRL_EShopping.Model.Products.Product;
import vrl.VRL_EShopping.Repo.ProductRepo;
import vrl.VRL_EShopping.Service.Interfaces.ProductService;

import java.util.HashMap;
import java.util.List;
@Service
public class ProdService implements ProductService {
    @Autowired
    ProductRepo prp1;
    @Override
    public Product addProduct(ProdData p1) {
        int pid = (prp1.findAll()).size()+1;
        Product p = new Product();
        p.setProductId(pid);
        p.setD1(p1);
        return prp1.save(p);
    }

    @Override
    public List<Product> addProducts(List<ProdData> prods) {
        return null;
    }

    @Override
    public List<ProductService> getAllProductList() {
        return null;
    }

    @Override
    public List<ProductService> searchProductById(int pid) {
        return null;
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

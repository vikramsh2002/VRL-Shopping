package vrl.VRL_EShopping.Service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import vrl.VRL_EShopping.Model.Products.ProductData;
import vrl.VRL_EShopping.Model.Products.UniqueProd;
import vrl.VRL_EShopping.Repo.ProductRepo;
import vrl.VRL_EShopping.Service.Interfaces.ProductService;

import javax.websocket.OnError;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProdService implements ProductService {
    @Autowired
    ProductRepo prp1;
    @Autowired
    private MongoTemplate mongoTemplate;
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
    public List<ProductData> searchProductPriceRange(double low,double high) {
        // Specific price which product are available
        // Like 100rs : which things can I Buy

        //List<ProductData> d1 = prp1.findAll().stream().filter(e -> e.getProductPrice()<=price).collect(Collectors.toList());
        Query query = new Query();


        query.addCriteria(Criteria.where("productPrice").lte(high).gte(low));

        System.out.println(query.toString());
        return mongoTemplate.find(query,ProductData.class);
    }

    @Override
    public List<ProductData> searchProductAboveRating(double rating){
            Query query = new Query();
            query.addCriteria(Criteria.where("averageRating").gte(rating));
            return mongoTemplate.find(query,ProductData.class);
    }
    @Override
    public List<ProductData> searchProductByCategory(String category){
        Query query = new Query();
        query.addCriteria(Criteria.where("category").is(category));
        return mongoTemplate.find(query,ProductData.class);
    }
    @Override
    public List<ProductData> searchProductByDiscount(double discount){
        Query query = new Query();
        query.addCriteria(Criteria.where("discount").gte(discount));
        return mongoTemplate.find(query,ProductData.class);
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

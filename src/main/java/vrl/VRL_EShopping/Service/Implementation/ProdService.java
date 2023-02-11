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
import java.util.*;

@Service
public class ProdService implements ProductService {


    @Autowired
    ProductRepo prp1;
    @Autowired
    private MongoTemplate mongoTemplate;
    /*  ------------------------------------------------------------------------------------
     *     Products   Additions
     * ------------------------------------------------------------------------------------ */

    @Override
    public ProductData addProduct(ProductData p1) {
        return prp1.save(p1);
    }



    @Override
    public List<ProductData> getAllProductList() {
        return prp1.findAll();
    }


    /*  ------------------------------------------------------------------------------------
     *     Products   Searching
     * ------------------------------------------------------------------------------------ */
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


    /*  ------------------------------------------------------------------------------------
     *     Products   Updation
     * ------------------------------------------------------------------------------------ */
    @Override
    public String updatePrice(UniqueProd p1,double price) {
        try {
            Optional<ProductData> data = prp1.findById(p1);
            if (price >=0) {
                ProductData d1 = data.get();
                d1.setProductPrice(price);
                prp1.save(d1);
                return "Successfully Updated Price to "+price;
            }
            else
                return "Product Price cannot be zero or negative";

        }
        catch (Exception e){
            return "Product Not Found";
        }
    }

    @Override
    public String updateDiscount(UniqueProd p1 , double discount) {
        try {
            Optional<ProductData> data = prp1.findById(p1);
            if (discount >=0) {
                ProductData d1 = data.get();
                d1.setDiscount(discount);
                prp1.save(d1);
                return "Successfully Updated Price to "+discount;
            }
            else
                return "Product Price cannot be zero or negative";

        }
        catch (Exception e){
            return "Product Not Found";
        }

    }

    @Override
    public String updateReviewPid(UniqueProd p1, Map<String,String> userReview) {
        try {
            Optional<ProductData> data = prp1.findById(p1);
            if (!(userReview.isEmpty())) {
                ProductData d1 = data.get();
                d1.setReview(userReview);
                prp1.save(d1);
                return "Successfully Updated Review to "+userReview;
            }
            else
                return "Product Review cannot be empty";

        }
        catch (Exception e){
            return "Product Not Found";
        }

    }

    @Override
    public String updateAvgRating(UniqueProd p1, double newRating) {
        try {
            Optional<ProductData> data = prp1.findById(p1);
            if (newRating >=0 && newRating<=5) {
                ProductData d1 = data.get();
                d1.setAverageRating(newRating);
                prp1.save(d1);
                return "Successfully Updated Price to "+newRating;
            }
            else
                return "Product Rating must be in range 0-5";

        }
        catch (Exception e){
            return "Product Not Found";
        }
    }

    @Override
    public String updateOrderStocks(UniqueProd p1, boolean oos) {
        try {
            Optional<ProductData> data = prp1.findById(p1);

                ProductData d1 = data.get();
                d1.setOutOfStock(oos);
                prp1.save(d1);
                return "Successfully Updated Stock Status to "+oos;
        }
        catch (Exception e){
            return "Product Not Found";
        }
    }

    @Override
    public String updateCategory(UniqueProd p1, String Category) {
        try {
            Optional<ProductData> data = prp1.findById(p1);
            if (!( Category.isEmpty())) {
                ProductData d1 = data.get();
                d1.setCategory(Category);
                prp1.save(d1);
                return "Successfully Updated Category to "+Category;
            }
            else
                return "Product Category Cannot be Null or Empty";

        }
        catch (Exception e){
            return "Product Not Found";
        }
    }
    @Override
    public String updateImgUrl(UniqueProd p1,String img){

        try {
            Optional<ProductData> data = prp1.findById(p1);
            if (!( img.isEmpty())) {
                ProductData d1 = data.get();
                d1.setImgurl(img);
                prp1.save(d1);
                return "Successfully Updated Image Url to "+img;
            }
            else
                return "Product Image Url can't be null or  empty string";

        }
        catch (Exception e){
            return "Product Not Found";
        }

    }
}

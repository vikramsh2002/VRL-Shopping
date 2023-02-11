package vrl.VRL_EShopping.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vrl.VRL_EShopping.Model.Products.ProductData;
import vrl.VRL_EShopping.Model.Products.UniqueProd;
import vrl.VRL_EShopping.Model.SearchRequest.SearchAboveDiscount;
import vrl.VRL_EShopping.Model.SearchRequest.SearchAboveRating;
import vrl.VRL_EShopping.Model.SearchRequest.SearchByCategory;
import vrl.VRL_EShopping.Model.SearchRequest.SearchInPriceRange;
import vrl.VRL_EShopping.Service.Interfaces.ProductService;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService ps;
    /*  ------------------------------------------------------------------------------------
     *        Products Additions
     * ------------------------------------------------------------------------------------ */
    @RequestMapping(value = "/addNewProduct",method = RequestMethod.POST)
    ProductData add(@RequestBody ProductData d1){
        System.out.println("Reviews : "+d1.getReview());
        return ps.addProduct(d1);
    }

    /*  ------------------------------------------------------------------------------------
     *     Products   Updation
     * ------------------------------------------------------------------------------------ */



    /*  ------------------------------------------------------------------------------------
    *     Product   Searching
    * ------------------------------------------------------------------------------------ */
    @RequestMapping(value = "/getByProductName&Brand")
    ProductData getById(@RequestBody UniqueProd id){
        return ps.searchProductById(id);
    }

    @RequestMapping(value="/ProductsInPriceRange")
    List<ProductData> getprodsByprice(@RequestBody SearchInPriceRange price){
        return ps.searchProductPriceRange(price.getLowprice(),price.getHighprice());
    }

    @RequestMapping(value = "/ProductAboveRated")
    List<ProductData> getprodByRating(@RequestBody SearchAboveRating rating){
        return ps.searchProductAboveRating(rating.getRating());
    }

    @RequestMapping(value = "/ProductByCategory")
    List<ProductData> getprodByRating(@RequestBody SearchByCategory category){
        return ps.searchProductByCategory(category.getCategory());
    }

    @RequestMapping(value = "/ProductByDiscountPercentAbove")
    List<ProductData> getprodByRating(@RequestBody SearchAboveDiscount discount){
        return ps.searchProductByDiscount(discount.getDiscount());
    }



    

}

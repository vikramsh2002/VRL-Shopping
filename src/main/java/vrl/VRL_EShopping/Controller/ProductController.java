package vrl.VRL_EShopping.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vrl.VRL_EShopping.Model.Products.ProductData;
import vrl.VRL_EShopping.Model.Products.UniqueProd;
import vrl.VRL_EShopping.Model.Products.SearchRequest.SearchAboveDiscount;
import vrl.VRL_EShopping.Model.Products.SearchRequest.SearchAboveRating;
import vrl.VRL_EShopping.Model.Products.SearchRequest.SearchByCategory;
import vrl.VRL_EShopping.Model.Products.SearchRequest.SearchInPriceRange;
import vrl.VRL_EShopping.Model.Products.UpdateRequest.UpdateBoolean;
import vrl.VRL_EShopping.Model.Products.UpdateRequest.UpdateFloats;
import vrl.VRL_EShopping.Model.Products.UpdateRequest.UpdateMap;
import vrl.VRL_EShopping.Model.Products.UpdateRequest.UpdateString;
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
    @RequestMapping(value = "/UpdatePrice", method = RequestMethod.POST)
    String UpdatePrice(@RequestBody UpdateFloats p1){
        return ps.updatePrice(p1.getProdId(),p1.getData());
    }

    @RequestMapping(value = "/UpdateDiscount", method = RequestMethod.POST)
    String UpdateDis(@RequestBody UpdateFloats p1){
        return ps.updateDiscount(p1.getProdId(),p1.getData());
    }

    @RequestMapping(value = "/UpdateRating", method = RequestMethod.POST)
    String UpdateRating(@RequestBody UpdateFloats p1){
        return ps.updateAvgRating(p1.getProdId(),p1.getData());
    }

    @RequestMapping(value = "/UpdateOutOfStockStatus", method = RequestMethod.POST)
    String UpdateOOSS(@RequestBody UpdateBoolean p1){
        return ps.updateOrderStocks(p1.getProdId(),p1.isData());
    }

    @RequestMapping(value = "/UpdateImageURL", method = RequestMethod.POST)
    String Updateimg(@RequestBody UpdateString p1){
        return ps.updateImgUrl(p1.getProdId(),p1.getData());
    }

    @RequestMapping(value = "/UpdateCategory", method = RequestMethod.POST)
    String UpdateCategory(@RequestBody UpdateString p1){
        return ps.updateCategory(p1.getProdId(),p1.getData());
    }

    @RequestMapping(value = "/UpdateReviews", method = RequestMethod.POST)
    String Updateimg(@RequestBody UpdateMap p1){
        return ps.updateReviewPid(p1.getProdId(),p1.getData());
    }


//    /*  ------------------------------------------------------------------------------------
//     *     Product   Deletion
//     * ------------------------------------------------------------------------------------ */
//      @RequestMapping(value = "/RemovePrductById",method = RequestMethod.POST)
//      String removebyid(@RequestBody UniqueProd p1){
//          return ps.deleteProductById(p1);
//      }


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

    // get all the data
    @RequestMapping(value = "/AllProducts")
    List<ProductData> getAllProducts(){
        return ps.getAllProductList();
    }



}

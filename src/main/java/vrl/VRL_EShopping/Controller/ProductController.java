package vrl.VRL_EShopping.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vrl.VRL_EShopping.Model.Products.ProductData;
import vrl.VRL_EShopping.Model.Products.UniqueProd;
import vrl.VRL_EShopping.Service.Interfaces.ProductService;

@RestController
public class ProductController {
    @Autowired
    ProductService ps;
    @RequestMapping(value = "/addNewProduct",method = RequestMethod.POST)
    ProductData add(@RequestBody ProductData d1){
        System.out.println("Reviews : "+d1.getReview());
        return ps.addProduct(d1);
    }


    @RequestMapping(value = "/getByProductName&Brand")
    ProductData getById(@RequestBody UniqueProd id){
        return ps.searchProductById(id);
    }


}

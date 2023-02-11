package vrl.VRL_EShopping.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vrl.VRL_EShopping.Model.Carts.Request.addItemRequest;
import vrl.VRL_EShopping.Service.Interfaces.CartService;

@RestController
public class CartController {

    @Autowired
    CartService cartService;

    @RequestMapping(value = "/AddItemToCart",method = RequestMethod.POST)
    String addItem(@RequestBody addItemRequest request){
        System.out.println(request.getCartitem().getId().getBrand());
        System.out.println(request.getCartitem().getId().getProductName());
        return cartService.addItemToCart(request.getUsername(),request.getCartitem());
    }

}

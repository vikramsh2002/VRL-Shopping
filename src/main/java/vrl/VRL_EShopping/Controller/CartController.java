package vrl.VRL_EShopping.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vrl.VRL_EShopping.Model.Carts.Cart;
import vrl.VRL_EShopping.Model.Carts.Request.RequestByUser;
import vrl.VRL_EShopping.Model.Carts.Request.RequestByUserAndProduct;
import vrl.VRL_EShopping.Model.Carts.Request.RequestUserQtyProduct;
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


    @RequestMapping(value = "/ShowCart",method = RequestMethod.POST)
    Cart show(@RequestBody RequestByUser request){
        return cartService.getCartItems(request.getUsername());
    }

    @RequestMapping(value = "/EmptyCart",method = RequestMethod.POST)
    String EmptyCart(@RequestBody RequestByUser request){
        return cartService.EmptyCart(request.getUsername());
    }

    @RequestMapping(value = "/RemoveItemFromCart",method = RequestMethod.POST)
    String RomoveItemFromCart(@RequestBody RequestByUserAndProduct request){
        return cartService.deleteItemFromCart(request.getUsername(),request.getProdId());
    }

    @RequestMapping(value = "/ChangeQtyOfItem",method = RequestMethod.POST)
    String UpdateQty(@RequestBody RequestUserQtyProduct request){
        return cartService.UpdateQuantity(request.getUsername(),request.getQuantity(),request.getProdId());
    }











}

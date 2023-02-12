package vrl.VRL_EShopping.Service.Interfaces;

import vrl.VRL_EShopping.Model.Carts.Cart;
import vrl.VRL_EShopping.Model.Carts.CartItem;
import vrl.VRL_EShopping.Model.Products.UniqueProd;

public interface CartService {
    // add+update+delete+fetch

    String addItemToCart(String username,CartItem c1);

    String deleteItemFromCart(String username, UniqueProd p1);

     String EmptyCart(String user);

    String UpdateQuantity(String user,int newQty, UniqueProd p1);

    Cart getCartItems(String user);

}

package vrl.VRL_EShopping.Service.Interfaces;

import vrl.VRL_EShopping.Model.Carts.Cart;
import vrl.VRL_EShopping.Model.Carts.CartItem;

public interface CartService {
    // add+update+delete+fetch

    String addItemToCart(String username,CartItem c1);

    void deleteItemFromCart(int cartId);

    void EmptyCart(Cart c1);

    void UpdateQuantity(Cart c1,int newQty, int cartItemid);

    Cart getCartItems(Cart c1);

}

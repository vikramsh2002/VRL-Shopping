package vrl.VRL_EShopping.Service.Interfaces;

import vrl.VRL_EShopping.Model.CartEntity.Cart;
import vrl.VRL_EShopping.Model.CartEntity.CartItem;

public interface CartService {
    // add+update+delete+fetch

    void addItemToCart(CartItem c1, int quantity);
    void addAllItemToCart(Cart c1);
    void deleteItemFromCart(int cartId);

    void EmptyCart(Cart c1);
    void UpdateQuantity(Cart c1,int newQty, int cartItemid);

    Cart getCartItems(Cart c1);

}

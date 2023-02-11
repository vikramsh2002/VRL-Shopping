package vrl.VRL_EShopping.Service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vrl.VRL_EShopping.Model.Carts.Cart;
import vrl.VRL_EShopping.Model.Carts.CartItem;
import vrl.VRL_EShopping.Repo.CartRepo;
import vrl.VRL_EShopping.Repo.ProductRepo;
import vrl.VRL_EShopping.Service.Interfaces.CartService;

import java.util.ArrayList;
import java.util.List;
@Service
public class CartServices implements CartService {
    @Autowired
    CartRepo cartRepo;
    @Autowired
    ProductRepo productRepo;
    @Override
    public String addItemToCart(String user,CartItem cartItem) {
        if(cartRepo.existsById(user)){
            Cart usercart=cartRepo.findById(user).get();
            List<CartItem> prev=usercart.getCartitems();
            prev.add(cartItem);

            if(productRepo.existsById(cartItem.getId())) {
                double priceEach=productRepo.findById(cartItem.getId()).get().getProductPrice();

                double newtotal = usercart.getTotalprice() + cartItem.getQuantity() * priceEach;

                // update locally
                usercart.setTotalprice(newtotal);
                usercart.setCartitems(prev);

                // update in mongodb
                cartRepo.save(usercart);
                return "Successfully Added Item to Cart...";
            }
            else{
                return "No Product Found";
            }
        }
        else {
           if(productRepo.existsById(cartItem.getId()))
           {
                Cart usercart = new Cart();

                List<CartItem> lc = new ArrayList<CartItem>();
                lc.add(cartItem);

                double totprice = cartItem.getQuantity() * productRepo.findById(cartItem.getId()).get().getProductPrice();

                // locally update
                usercart.setUsername(user);
                usercart.setTotalprice(totprice);
                usercart.setCartitems(lc);

                // update in mongodb
                cartRepo.save(usercart);
                return "Successfully Added Item to Cart...";
            }
            else{
                return "No Product Found";
            }

        }
    }


    @Override
    public void deleteItemFromCart(int cartId) {

    }

    @Override
    public void EmptyCart(Cart c1) {

    }

    @Override
    public void UpdateQuantity(Cart c1, int newQty, int cartItemid) {

    }

    @Override
    public Cart getCartItems(Cart c1) {
        return null;
    }
}

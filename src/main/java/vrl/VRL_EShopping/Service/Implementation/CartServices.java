package vrl.VRL_EShopping.Service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import vrl.VRL_EShopping.Model.Carts.Cart;
import vrl.VRL_EShopping.Model.Carts.CartItem;
import vrl.VRL_EShopping.Model.Products.UniqueProd;
import vrl.VRL_EShopping.Repo.CartRepo;
import vrl.VRL_EShopping.Repo.ProductRepo;
import vrl.VRL_EShopping.Repo.UserRepo;
import vrl.VRL_EShopping.Service.Interfaces.CartService;

import java.util.ArrayList;
import java.util.List;
@Service
public class CartServices implements CartService {
    @Autowired
    CartRepo cartRepo;
    @Autowired
    ProductRepo productRepo;
    @Autowired
    UserRepo userRepo;
    @Override
    public String addItemToCart(String user,CartItem cartItem) {
        if(userRepo.existsById(user)) {
            if (cartRepo.existsById(user)) {
                Cart usercart = cartRepo.findById(user).get();
                List<CartItem> prev = usercart.getCartitems();
                prev.add(cartItem);

                if (productRepo.existsById(cartItem.getId())) {
                    double priceEach = productRepo.findById(cartItem.getId()).get().getProductPrice();

                    double newtotal = usercart.getTotalprice() + cartItem.getQuantity() * priceEach;

                    // update locally
                    usercart.setTotalprice(newtotal);
                    usercart.setCartitems(prev);

                    // update in mongodb
                    cartRepo.save(usercart);
                    return "Successfully Added Item to Cart...";
                } else {
                    return "No Product Found";
                }
            } else {
                if (productRepo.existsById(cartItem.getId())) {
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
                } else {
                    return "No Product Found";
                }

            }
        }
        else{
            return "Username Doesn't Exist";
        }
    }


    @Override
    public Cart getCartItems(String user) {
        if(cartRepo.existsById(user))
        {
            return cartRepo.findById(user).get();
        }
        else{
            return null;
        }
    }

    @Override
    public String deleteItemFromCart(String user, UniqueProd p1) {
        /*
        * @User: Specific user whose cart need to alter
        * @p1: id of unique prod
        * */
        if(cartRepo.existsById(user)){
            Cart userCart = cartRepo.findById(user).get();
            double newtotal = userCart.getTotalprice();
            int index=-1;
            for(CartItem item :userCart.getCartitems()){
                index+=1;
                if( item.getId().getBrand().equals(p1.getBrand()) && item.getId().getProductName().equals(p1.getProductName())){
                    newtotal -= item.getQuantity() * productRepo.findById(item.getId()).get().getProductPrice();
                    break;
                }

            }
            if(index!=-1)
            {
                List<CartItem> tmp=userCart.getCartitems();
                tmp.remove(index);

                userCart.setCartitems(tmp);
                userCart.setTotalprice(newtotal);

                cartRepo.save(userCart);
                return "Successfully Removed ..";

            }
            else{
                return "Item not in cart...";

            }

        }
        else{
            return "User Doesn't Exist";

        }
    }

    @Override
    public String EmptyCart(String user) {
        if(cartRepo.existsById(user)){
            cartRepo.deleteById(user);
            return "Successfully Empty...";
        }
        else{
            return "Already Empty..";
        }
    }

    @Override
    public String UpdateQuantity(String user, int newQty, UniqueProd p1) {
        if(cartRepo.existsById(user)){

            if(newQty>=0)
            {
                Cart userCart = cartRepo.findById(user).get();
                double newtotal=userCart.getTotalprice();
                int index=-1;
                for(CartItem item :userCart.getCartitems()){
                    index+=1;
                    if( item.getId().getBrand().equals(p1.getBrand()) && item.getId().getProductName().equals(p1.getProductName())){
                        newtotal+=((newQty-item.getQuantity())*productRepo.findById(p1).get().getProductPrice());
                        break;
                    }

                }
                if(index!=-1)
                {
                    List<CartItem> tmp=userCart.getCartitems();
                    CartItem itemupdate=new CartItem();
                    itemupdate.setQuantity(newQty);
                    itemupdate.setId(p1);

                    tmp.set(index,itemupdate);

                    userCart.setCartitems(tmp);
                    userCart.setTotalprice(newtotal);

                    cartRepo.save(userCart);
                    return "Successfully Updated Quantity ..";
                }
                else{
                    return "No Item  in Cart to Update Qty..";
                }

            }
            else{
                return "Quantity Can't be Negative..";
            }

        }
        else{
            return "Cart is Empty..";
        }

    }



}

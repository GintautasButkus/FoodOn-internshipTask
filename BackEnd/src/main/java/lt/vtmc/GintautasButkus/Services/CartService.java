package lt.vtmc.GintautasButkus.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.vtmc.GintautasButkus.Exceptions.NoDishExistException;
import lt.vtmc.GintautasButkus.Models.Cart;
import lt.vtmc.GintautasButkus.Models.Dish;
import lt.vtmc.GintautasButkus.Models.User;
import lt.vtmc.GintautasButkus.Repositories.CartRepository;
import lt.vtmc.GintautasButkus.dto.AddToCartDto;
import lt.vtmc.GintautasButkus.dto.CartDto;
import lt.vtmc.GintautasButkus.dto.CartItemDto;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CartService {

    @Autowired
    private  CartRepository cartRepository;

    public CartService(){}

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void addToCart(AddToCartDto addToCartDto, Dish dish, User user){
        Cart cart = new Cart(dish, addToCartDto.getQuantity(), user);
        cartRepository.save(cart);
    }


    public CartDto listCartItems(User user) {
        List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);
        List<CartItemDto> cartItems = new ArrayList<>();
        for (Cart cart:cartList){
            CartItemDto cartItemDto = getDtoFromCart(cart);
            cartItems.add(cartItemDto);
        }
        return new CartDto(cartItems);
    }


    public static CartItemDto getDtoFromCart(Cart cart) {
        return new CartItemDto(cart);
    }


    public void updateCartItem(AddToCartDto cartDto, User user, Dish dish){
        Cart cart = cartRepository.findById(cartDto.getId()).get();
        cart.setQuantity(cartDto.getQuantity());
        cart.setCreatedDate(new Date());
        cartRepository.save(cart);
    }

    public void deleteCartItem(int id,int userId) throws NoDishExistException {
        if (!cartRepository.existsById(id))
            throw new NoDishExistException("Cart id is invalid : " + id);
        cartRepository.deleteById(id);

    }

    public void deleteCartItems(Long userId) {
        cartRepository.deleteAll();
    }


    public void deleteUserCartItems(User user) {
        cartRepository.deleteByUser(user);
    }

}

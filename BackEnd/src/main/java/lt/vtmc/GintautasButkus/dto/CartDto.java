package lt.vtmc.GintautasButkus.dto;

import java.util.List;

public class CartDto {
    private List<CartItemDto> cartItems;

    public CartDto(List<CartItemDto> cartItemDtoList) {
        this.cartItems = cartItemDtoList;
    }

    public List<CartItemDto> getcartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemDto> cartItemDtoList) {
        this.cartItems = cartItemDtoList;
    }


}

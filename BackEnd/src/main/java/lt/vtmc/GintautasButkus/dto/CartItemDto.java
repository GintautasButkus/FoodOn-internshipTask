package lt.vtmc.GintautasButkus.dto;

import javax.validation.constraints.NotNull;

import lt.vtmc.GintautasButkus.Models.Cart;
import lt.vtmc.GintautasButkus.Models.Dish;

public class CartItemDto {
    private Integer id;
    private @NotNull Integer quantity;
    private @NotNull Dish dish;

    public CartItemDto() {
    }

    public CartItemDto(Cart cart) {
        this.setId(cart.getId());
        this.setQuantity(cart.getQuantity());
        this.setDish(cart.getDish());
    }

    @Override
    public String toString() {
        return "CartDto{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", productName=" + dish.getDishName() +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

	public Dish getDish() {
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}
    
    

}

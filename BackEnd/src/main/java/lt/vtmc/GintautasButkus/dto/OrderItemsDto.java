package lt.vtmc.GintautasButkus.dto;

import com.sun.istack.NotNull;

public class OrderItemsDto {
	
    private @NotNull int quantity;
    private @NotNull int orderId;
    private @NotNull int productId;

    
    public OrderItemsDto () {}

    public OrderItemsDto(@NotNull double price, @NotNull int quantity, @NotNull int orderId, @NotNull int productId) {
        this.quantity = quantity;
        this.orderId = orderId;
        this.productId = productId;
    }

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
    
    
    
}

package lt.vtmc.GintautasButkus.dto;

import com.sun.istack.NotNull;

import lt.vtmc.GintautasButkus.Models.Order;

public class PlaceOrderDto {

	private Integer id;
	private @NotNull Long userId;

	public PlaceOrderDto() {
	}

	public PlaceOrderDto(Order order) {
		this.setId(order.getId());
		this.setUserId(order.getUserId());
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	

}

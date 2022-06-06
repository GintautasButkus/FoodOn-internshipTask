package lt.vtmc.GintautasButkus.dto;

import com.sun.istack.NotNull;

import lt.vtmc.GintautasButkus.Models.Order;

public class OrderDto {
	
	private Integer id;
	
	@NotNull
	private Long userId;
	
	public OrderDto(Order order) {
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

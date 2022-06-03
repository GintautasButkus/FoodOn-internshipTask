package lt.vtmc.GintautasButkus.Models;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "Order Items")
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderItemId;

	@NotBlank
	private int quantity;
	
	@NotBlank
	private LocalDateTime orderItemDate;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dishId", referencedColumnName = "dishId")
	private Dish dish;
	
//	@ManyToOne(fetch = FetchType.LAZY, optional = false)
//	@JoinColumn(name = "orderId", nullable = true)
//	@OnDelete(action = OnDeleteAction.CASCADE)
//	@JsonIgnore
//	private Order order;
	
	public OrderItem() {}

	public OrderItem(@NotBlank int quantity, @NotBlank LocalDateTime orderItemDate) {
		this.quantity = quantity;
		this.orderItemDate = orderItemDate;
	}

	public Long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public LocalDateTime getOrderItemDate() {
		return orderItemDate;
	}

	public void setOrderItemDate(LocalDateTime orderItemDate) {
		this.orderItemDate = orderItemDate;
	}

	public Dish getDish() {
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}
	
	
	
	
	

}

package lt.vtmc.GintautasButkus.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "orderitems")
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderItemId;

	@Column(name = "quantity")
	private @NotNull int quantity;

	@Column(name = "created_date")
	private LocalDateTime createdDate;

	@ManyToOne
	@JoinColumn(name = "order_id", referencedColumnName = "id", insertable = true, updatable = true)
	private Order order;

	@OneToOne
	@JoinColumn(name = "dish_id", referencedColumnName = "id", insertable = true, updatable = true)
	private Dish dish;

	public OrderItem() {
	}

	public OrderItem(@NotNull int quantity, @NotNull LocalDateTime createDate) {
		this.quantity = quantity;
		this.createdDate = createDate;
	}

	public Integer getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Dish getDish() {
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

}

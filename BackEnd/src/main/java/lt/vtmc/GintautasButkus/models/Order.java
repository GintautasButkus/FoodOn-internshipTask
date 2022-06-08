package lt.vtmc.GintautasButkus.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "order")
public class Order {

	@Id
	private String id;

	@Column(name = "user_id")
	private @NotBlank Long userId;

	@Column(name = "created_date")
	private LocalDateTime createdDate;
	
	@NotNull
	@Column(name = "status")
	private String status;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "order_id", referencedColumnName = "id", insertable = false, updatable = false)
	@JsonIgnore
	private List<OrderItem> orderItems;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
	private User user;

	public Order() {
	}

	public Order(String id, @NotBlank Long userId, LocalDateTime createdDate, String status) {
		this.id = id;
		this.userId = userId;
		this.createdDate = createdDate;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(@NotBlank Long userId) {
		this.userId = userId;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}


	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(@NotNull String status) {
		this.status = status;
	}
	
	
}

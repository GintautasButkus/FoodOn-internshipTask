package lt.vtmc.GintautasButkus.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

@Entity
@Table(name = "cart")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "created_date")
	private Date createdDate;

	@ManyToOne
	@JoinColumn(name = "dish_id", referencedColumnName = "id")
	private Dish dish;

	@JsonIgnore
	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "user_id")
	private User user;

	private int quantity;

	public Cart() {
	}

	public Cart(Dish dish, int quantity, User user) {
		this.user = user;
		this.dish = dish;
		this.quantity = quantity;
		this.createdDate = new Date();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Dish getDish() {
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

}

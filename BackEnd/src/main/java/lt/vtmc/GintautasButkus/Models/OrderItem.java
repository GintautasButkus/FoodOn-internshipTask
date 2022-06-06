package lt.vtmc.GintautasButkus.Models;

import java.util.Date;

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

    @Column(name = "dishId")
    private @NotNull Long dishId;

    @Column(name = "quantity")
    private @NotNull int quantity;

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "created_date")
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "order_id",referencedColumnName = "id",insertable = false,updatable = false)
    private Order order;

    @OneToOne
    @JoinColumn(name = "dish_Dish_id",referencedColumnName = "id",insertable = false,updatable = false)
    private Dish dish;

    public OrderItem(){}

    public OrderItem(Integer orderId, @NotNull Long dish_id, @NotNull int quantity) {
        this.dishId = dish_id;
        this.quantity = quantity;
        this.orderId=orderId;
        this.createdDate = new Date();
    }

	public Integer getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}

	

	public Long getDishId() {
		return dishId;
	}

	public void setDishId(Long dishId) {
		this.dishId = dishId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
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

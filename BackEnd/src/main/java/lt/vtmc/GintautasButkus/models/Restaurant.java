package lt.vtmc.GintautasButkus.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "restaurants")
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String restaurantName;

	@NotBlank
	private String restaurantCode;

	@NotBlank
	@Size(max = 50)
	private String address;
	
	//	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
//	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
//	private Set<Role> roles = new HashSet<>();

	
	public Restaurant() {}

	public Restaurant(@NotBlank @Size(max = 20) String restaurantName,
			@NotBlank @Size(max = 6) String restaurantCode, @NotBlank @Size(max = 50) String address) {
		this.restaurantName = restaurantName;
		this.restaurantCode = restaurantCode;
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getRestaurantCode() {
		return restaurantCode;
	}

	public void setRestaurantCode(String restaurantCode) {
		this.restaurantCode = restaurantCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	


	

}

package lt.vtmc.GintautasButkus.Controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import lt.vtmc.GintautasButkus.Models.Menu;
import lt.vtmc.GintautasButkus.Models.OrderItem;
import lt.vtmc.GintautasButkus.Models.Restaurant;
import lt.vtmc.GintautasButkus.PayloadRequest.SignupRequest;
import lt.vtmc.GintautasButkus.Services.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@Api(value = "", tags = { "User Board" })
@Tag(name = "User Board", description = "Food On App Users")
@RestController
@RequestMapping("/api/auth/user")
@PreAuthorize("hasRole('ROLE_USER')")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/signup")
	public ResponseEntity<?> createUser(@Valid @RequestBody SignupRequest signUpRequest) {
		return userService.registerUser(signUpRequest);
	}
	
	@GetMapping("/find_restaurant/{nameFragment}")
	public List<Restaurant> getRestaurantsByName(@PathVariable String nameFragment) {
		return userService.getRestaurantsByName(nameFragment);
	}
	
	@GetMapping("/select_restaurant/{id}")
	public Restaurant selectRestaurant(@PathVariable Long id) {
		return userService.getRestaurantsById(id);
	}
	
	@GetMapping("/get_menus/{id}")
	public List<Menu> getRestaurantMenus(@PathVariable Long id) {
		return userService.getMenu(id);
	}
	
	@PostMapping("/create_order/{quantity}/{dishId}")
	public void createOrder(@PathVariable int quantity, @PathVariable Long dishId, @RequestBody OrderItem orderItemDetails) {
		userService.selectItemToOrder(dishId, quantity, orderItemDetails);
	}
	


}

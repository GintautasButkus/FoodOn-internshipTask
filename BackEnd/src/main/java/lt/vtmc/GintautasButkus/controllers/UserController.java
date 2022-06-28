package lt.vtmc.GintautasButkus.controllers;

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
import lt.vtmc.GintautasButkus.models.Dish;
import lt.vtmc.GintautasButkus.models.Menu;
import lt.vtmc.GintautasButkus.models.Restaurant;
import lt.vtmc.GintautasButkus.payloadRequest.SignupRequest;
import lt.vtmc.GintautasButkus.services.AdminService;
import lt.vtmc.GintautasButkus.services.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@Api(value = "", tags = { "User Board" })
@Tag(name = "User Board", description = "Food On App Users")
@RestController
@RequestMapping("/api/auth/user")

public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	
	@PostMapping("/signup")
	public ResponseEntity<?> createUser(@Valid @RequestBody SignupRequest signUpRequest) {
		return userService.registerUser(signUpRequest);
	}
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/restaurant/{nameFragment}")
	public List<Restaurant> getRestaurantsByName(@PathVariable String nameFragment) {
		return userService.getRestaurantsByName(nameFragment);
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/restaurant/{id}")
	public Restaurant selectRestaurant(@PathVariable Long id) {
		return userService.getRestaurantsById(id);
	}
	
//	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@GetMapping("/menu/{id}")
	public List<Menu> getRestaurantMenus(@PathVariable Long id) {
		return userService.getMenu(id);
	}
	
	@GetMapping("/dish/{id}")
	public List<Dish> getAllDishes(@PathVariable Long id) {
		return userService.getAllDishes(id);
	}
	
	@GetMapping("/restaurants")
	public List<Restaurant> getAllRestaurants(){
		return userService.getAllRestaurants();
	}
	
	}

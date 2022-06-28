package lt.vtmc.GintautasButkus.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import lt.vtmc.GintautasButkus.models.Dish;
import lt.vtmc.GintautasButkus.models.Menu;
import lt.vtmc.GintautasButkus.models.Restaurant;
import lt.vtmc.GintautasButkus.payloadRequest.SignupRequest;
import lt.vtmc.GintautasButkus.services.AdminService;
import lt.vtmc.GintautasButkus.services.DishService;
import lt.vtmc.GintautasButkus.services.MenuService;
import lt.vtmc.GintautasButkus.services.RestaurantService;


@CrossOrigin(origins = "*", maxAge = 3600)
@Api(value = "", tags = { "Admin Board" })
@Tag(name = "Admin Board", description = "Food On Admin")
@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	AdminService adminService;
	
	@Autowired
	DishService dishService;
	
	@Autowired
	MenuService menuService;
	
	@Autowired
	RestaurantService retaurantService;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/user")
	public ResponseEntity<?> createUser(@Valid @RequestBody SignupRequest signUpRequest) {
		return adminService.registerUserOrAdmin(signUpRequest);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/user/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable Long id) {
		adminService.deleteUser(id);
	}
	
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/restaurant")
	@ResponseStatus(HttpStatus.CREATED)
	public void addRestaurant(@RequestBody @Valid Restaurant restaurantDetails) {
		retaurantService.addRestaurant(restaurantDetails);
	}
	
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/restaurant/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteRestaurant(@PathVariable Long id) {
		retaurantService.deleteRestaurant(id);
	}
	
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/restaurant/{id}")
	public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Long id,
			@RequestBody Restaurant restaurantDetails) {
		return retaurantService.updateRestaurant(id, restaurantDetails);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/menu/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void addNewMenu(@PathVariable Long id, String menuName, @RequestBody Menu menuDetails) {
		menuService.addMenu(id, menuName, menuDetails);
	}
	
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/menu/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteMenu(@PathVariable Long id) {
		menuService.deleteMenu(id);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/menu/{id}")
	public ResponseEntity<Menu> updateMenu(@PathVariable Long id,
			@RequestBody Menu menuDetails) {
		return menuService.updateMenu(id, menuDetails);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/dish/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void addDishToTheMenu(@PathVariable Long id, String dishName, String dishDescription, @RequestBody Dish dishDetails) {
		dishService.addDish(id, dishName, dishDescription, dishDetails);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/dish/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDish(@PathVariable Long id) {
		dishService.deleteDish(id);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/dish/{id}")
	public ResponseEntity<Dish> updateDish(@PathVariable Long id,
			@RequestBody Dish dishDetails) {
		return dishService.updateDish(id, dishDetails);
	}
	
	@GetMapping("/restaurants")
	public List<Restaurant> getAllRestaurants(){
		return adminService.getAllRestaurants();
	}
	

}

package lt.vtmc.GintautasButkus.Services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lt.vtmc.GintautasButkus.Exceptions.NoRestaurantExistsException;
import lt.vtmc.GintautasButkus.Exceptions.NoUserExistsException;
import lt.vtmc.GintautasButkus.Models.Restaurant;
import lt.vtmc.GintautasButkus.Repositories.RestaurantRepository;

@Service
public class RestaurantService {
	
	@Autowired
	RestaurantRepository restaurantRepository;

	public void addRestaurant(String restaurantName, String address, Restaurant restaurantDetails) {
		String code = UUID.randomUUID().toString();
		Restaurant restaurant = new Restaurant(restaurantName, code, address);
		restaurantRepository.save(restaurant);
	}
	
	public void deleteRestaurant(Long id) {
		if (restaurantRepository.existsById(id)) {
			restaurantRepository.findAll().stream().filter(restaurant -> restaurant.getId() == id).findFirst().get();
			restaurantRepository.deleteById(id);
		} else {
			throw new NoUserExistsException("Sorry, there is no such restaurant.");
		}
	}
	
	public ResponseEntity<Restaurant> updateRestaurant(Long id, Restaurant restaurantDetails) {
		Restaurant restaurant = restaurantRepository.findById(id)
				.orElseThrow(() -> new NoRestaurantExistsException("No restaurant found with this ID" + id));
		restaurant.setAddress(restaurantDetails.getAddress());
		restaurant.setRestaurantName(restaurantDetails.getRestaurantName());

		Restaurant updatedRestaurant = restaurantRepository.save(restaurant);
		return ResponseEntity.ok(updatedRestaurant);
	}

}

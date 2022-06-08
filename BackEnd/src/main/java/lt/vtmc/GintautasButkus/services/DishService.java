package lt.vtmc.GintautasButkus.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lt.vtmc.GintautasButkus.exceptions.NoDishExistException;
import lt.vtmc.GintautasButkus.exceptions.NoMenuExistsException;
import lt.vtmc.GintautasButkus.models.Dish;
import lt.vtmc.GintautasButkus.repositories.DishRepository;
import lt.vtmc.GintautasButkus.repositories.MenuRepository;

@Service
public class DishService {
	
	@Autowired
	MenuRepository menuRepository;
	
	@Autowired
	DishRepository dishRepository;
	
	public void addDish(Long menuId, String dishName, String dishDescription, Dish dishDetails) {
		Dish dish = menuRepository.findById(menuId).map(menu -> {
			dishDetails.setDishName(dishName);
			dishDetails.setDishDescription(dishDescription);
			dishDetails.setMenu(menu);
			return dishRepository.save(dishDetails);
		}).orElseThrow(() -> new NoDishExistException("No menu exists with such ID " + menuId));
		dishRepository.save(dish);
	}
	
	public void deleteDish(Long id) {
		if (dishRepository.existsById(id)) {
			dishRepository.findAll().stream().filter(menu -> menu.getId() == id).findFirst().get();
			dishRepository.deleteById(id);
		} else {
			throw new NoMenuExistsException("Sorry, there is no such dish with ID " + id);
		}
	}
	
	public ResponseEntity<Dish> updateDish(Long id, Dish dishDetails) {
		Dish dish = dishRepository.findById(id)
				.orElseThrow(() -> new NoDishExistException("No dish found with this ID" + id));
		dish.setDishName(dishDetails.getDishName());
		dish.setDishDescription(dishDetails.getDishDescription());
		Dish updatedDish = dishRepository.save(dish);
		return ResponseEntity.ok(updatedDish);
	}

}

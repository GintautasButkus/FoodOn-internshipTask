package lt.vtmc.GintautasButkus.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lt.vtmc.GintautasButkus.exceptions.NoMenuExistsException;
import lt.vtmc.GintautasButkus.exceptions.NoRestaurantExistsException;
import lt.vtmc.GintautasButkus.models.Menu;
import lt.vtmc.GintautasButkus.repositories.MenuRepository;
import lt.vtmc.GintautasButkus.repositories.RestaurantRepository;

@Service
public class MenuService {
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired
	MenuRepository menuRepository;
	
	public void addMenu(Long restaurantId, String menuName, Menu menuDetails) {
		Menu menu = restaurantRepository.findById(restaurantId).map(restaurant -> {
			menuDetails.setMenuName(menuName);
			menuDetails.setRestaurant(restaurant);
			return menuRepository.save(menuDetails);
		}).orElseThrow(() -> new NoRestaurantExistsException("No restaurant exists with such ID " + restaurantId));
		menuRepository.save(menu);
	}
	
	public void deleteMenu(Long id) {
		if (menuRepository.existsById(id)) {
			menuRepository.findAll().stream().filter(menu -> menu.getMenuId() == id).findFirst().get();
			menuRepository.deleteById(id);
		} else {
			throw new NoMenuExistsException("Sorry, there is no such menu with name " + id);
		}
	}
	
	public ResponseEntity<Menu> updateMenu(Long id, Menu menuDetails) {
		Menu menu = menuRepository.findById(id)
				.orElseThrow(() -> new NoMenuExistsException("No menu found with this ID" + id));
		menu.setMenuName(menuDetails.getMenuName());

		Menu updatedMenu = menuRepository.save(menu);
		return ResponseEntity.ok(updatedMenu);
	}

}

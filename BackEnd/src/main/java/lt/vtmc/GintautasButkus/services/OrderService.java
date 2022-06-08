package lt.vtmc.GintautasButkus.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import lt.vtmc.GintautasButkus.exceptions.NoOrderExistsException;
import lt.vtmc.GintautasButkus.models.EOrderStatus;
import lt.vtmc.GintautasButkus.models.Menu;
import lt.vtmc.GintautasButkus.models.Order;
import lt.vtmc.GintautasButkus.models.OrderItem;
import lt.vtmc.GintautasButkus.models.Restaurant;
import lt.vtmc.GintautasButkus.repositories.DishRepository;
import lt.vtmc.GintautasButkus.repositories.MenuRepository;
import lt.vtmc.GintautasButkus.repositories.OrderItemRepository;
import lt.vtmc.GintautasButkus.repositories.OrderRepository;
import lt.vtmc.GintautasButkus.repositories.UserRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;


	@Autowired
	UserRepository userRepository;

	@Autowired
	MenuRepository menuRespository;

	@Autowired
	DishRepository dishRepository;

	@Autowired
	OrderItemRepository orderItemRepository;

	
//	**************** GET LOGGED USER CREDENTIALS ***************************************************
	public String getUsername() {
		String username = null;
		Object authentication = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (authentication instanceof UserDetails) {
			username = ((UserDetails) authentication).getUsername();
		} else {
			username = authentication.toString();
		}
		return username;
	}

	public long getLoggedInUserId() {
		String username = getUsername();
		return userRepository.findAll().stream().filter(user -> user.getUsername().equals(username)).findFirst().get()
				.getId();
	}
	
	
//	************************* CREATE AN ORDER *************************************************

	public void placeOrder(int quantity, long dishId) {
		Menu menu = dishRepository.findAll().stream().filter(dish -> dish.getId() == dishId).findFirst().get()
				.getMenu();
		Restaurant restaurant = menuRespository.findAll().stream()
				.filter(menu2 -> menu2.getMenuId() == menu.getMenuId()).findFirst().get().getRestaurant();

		if (orderRepository.findAllByUserId(getLoggedInUserId()).stream()
				.filter(order -> order.getId().equals(restaurant.getRestaurantName())).findFirst().isPresent()) {
			
			if ( orderItemRepository.findAll().stream().filter(item -> item.getDish().getId() == dishId)
					.findFirst().filter(filteredItem -> filteredItem.getOrder().getId().equals(restaurant.getRestaurantName())).isPresent()) {
				
				OrderItem existingOrderItem = orderItemRepository.findAll().stream().filter(item -> item.getDish().getId() == dishId)
						.findFirst().filter(filteredItem -> filteredItem.getOrder().getId().equals(restaurant.getRestaurantName())).get();
				
				existingOrderItem.setQuantity(existingOrderItem.getQuantity() + quantity);
				existingOrderItem.setCreatedDate(LocalDateTime.now());
				orderItemRepository.save(existingOrderItem);
				
			} else {
				OrderItem orderItem = new OrderItem(quantity, LocalDateTime.now());

				orderItem.setOrder(orderRepository.findAllByUserId(getLoggedInUserId()).stream()
						.filter(order -> order.getId().equals(restaurant.getRestaurantName())).findFirst().get());

				orderItem.setDish(dishRepository.findById(dishId).get());
				orderItemRepository.save(orderItem);
			}

			
		} else {
			Order newOrder = new Order(restaurant.getRestaurantName(), getLoggedInUserId(), LocalDateTime.now(), EOrderStatus.ORDER_INPROGRESS.getLabel());
			orderRepository.save(newOrder);

			OrderItem newOrderItem2 = new OrderItem(quantity, LocalDateTime.now());
			newOrderItem2.setOrder(newOrder);
			newOrderItem2.setDish(dishRepository.findById(dishId).get());

			orderItemRepository.save(newOrderItem2);
		}
	}
	
	
//	********************* ADMIN TO CHANGE ORDER STATUS TO CONFIRMED *******************************************
	public ResponseEntity<Order> updateOrderStatus(Long userId, String orderId) {
		Order order = orderRepository.findById(orderId).stream().filter(order1 -> order1.getUserId()== userId).findFirst()
				.orElseThrow(() -> new NoOrderExistsException("No corder found for the user"));
		order.setStatus(EOrderStatus.ORDER_CONFIRMED.getLabel());

		Order updatedOrder = orderRepository.save(order);
		return ResponseEntity.ok(updatedOrder);
	}
	
//	*********************** ADMIN TO GET UNCONFIRMED ORDERS **************************************************
	public List<Order> getUnconfirmedOrders(){
		return orderRepository.findAll().stream().filter(order -> order.getStatus().equals("Not confirmed")).collect(Collectors.toList());
	}
	
//*********************  GET ORDER ITEMS OF THE ORDER BY USER *******************************************
	public List<OrderItem> getOrderItems(Long userId, String orderId) {
		return orderItemRepository.findAll().stream().filter(item -> item.getOrder().getId().equals(orderId)).filter(filteredItem -> filteredItem.getOrder().getUserId() == userId).collect(Collectors.toList());
	}
}

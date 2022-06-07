package lt.vtmc.GintautasButkus.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import lt.vtmc.GintautasButkus.Models.Order;
import lt.vtmc.GintautasButkus.Models.OrderItem;
import lt.vtmc.GintautasButkus.Services.OrderService;

@CrossOrigin(origins = "*", maxAge = 3600)
@Api(value = "", tags = { "Order Board" })
@Tag(name = "Order Board", description = "Make an order for user.")
@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@PostMapping("/add/{dishId}/{quantity}")
    public void placeOrder(@PathVariable Long dishId, @PathVariable int quantity){
        orderService.placeOrder(quantity, dishId);
    }
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/confirm/{userId}/{orderId}")
	public ResponseEntity<Order> updateOrderStatus(@PathVariable Long userId, @PathVariable String orderId){
		return orderService.updateOrderStatus(userId, orderId);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/unconfirmed")
	public List<Order> getunconfirmedOrders(){
		return orderService.getUnconfirmedOrders();
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/orderItemsAdmin/{userId}/{orderId}")
	public List<OrderItem> getOrderItemsOfUserForAdmin(@PathVariable Long userId, @PathVariable String orderId){
		return orderService.getOrderItems(userId, orderId);
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/orderItemsUser/{orderId}")
	public List<OrderItem> getOrderItemsOfUserForUser(@PathVariable String orderId){
		return orderService.getOrderItems(orderService.getLoggedInUserId(), orderId);
	}
	

}

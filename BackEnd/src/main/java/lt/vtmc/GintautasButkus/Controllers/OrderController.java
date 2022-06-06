package lt.vtmc.GintautasButkus.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import lt.vtmc.GintautasButkus.Models.Order;
import lt.vtmc.GintautasButkus.Services.OrderService;

@CrossOrigin(origins = "*", maxAge = 3600)
@Api(value = "", tags = { "Order Board" })
@Tag(name = "Order Board", description = "Make an order for user.")
@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@PostMapping("/add/{userId}")
    public void placeOrder(@PathVariable Long userId){
        orderService.placeOrder(userId);
    }
	
	@GetMapping("/{userId}")
    public ResponseEntity<List<Order>> getAllOrders(@PathVariable Long userId) {
        List<Order> orderDtoList = orderService.listOrders(userId);
        return new ResponseEntity<>(orderDtoList,HttpStatus.OK);
    }

}

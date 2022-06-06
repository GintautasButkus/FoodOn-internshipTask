package lt.vtmc.GintautasButkus.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.vtmc.GintautasButkus.Models.Order;
import lt.vtmc.GintautasButkus.Models.OrderItem;
import lt.vtmc.GintautasButkus.Models.User;
import lt.vtmc.GintautasButkus.Repositories.OrderRepository;
import lt.vtmc.GintautasButkus.Repositories.UserRepository;
import lt.vtmc.GintautasButkus.dto.CartDto;
import lt.vtmc.GintautasButkus.dto.CartItemDto;
import lt.vtmc.GintautasButkus.dto.PlaceOrderDto;

@Service
public class OrderService {
	@Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    OrderItemsService orderItemsService;
    
    @Autowired
    UserRepository userRepository;
    
        public void placeOrder(long userId) {
        User user = userRepository.findById(userId).get();
        CartDto cartDto = cartService.listCartItems(user);

        PlaceOrderDto placeOrderDto = new PlaceOrderDto();
        placeOrderDto.setUserId(userId);

        int orderId = saveOrder(placeOrderDto, userId);
        List<CartItemDto> cartItemDtoList = cartDto.getcartItems();
        for (CartItemDto cartItemDto : cartItemDtoList) {
            OrderItem orderItem = new OrderItem(
                    orderId,
                    cartItemDto.getDish().getId(),
                    cartItemDto.getQuantity());
            orderItemsService.addOrderedProducts(orderItem);
        }
        cartService.deleteCartItems(userId);
    }

    public int saveOrder(PlaceOrderDto orderDto, Long userId){
        Order order = getOrderFromDto(orderDto,userId);
        return orderRepository.save(order).getId();
    }

    private Order getOrderFromDto(PlaceOrderDto orderDto, Long userId) {
        Order order = new Order(orderDto,userId);
        return order;
    }

    public List<Order> listOrders(Long userId) {
        List<Order> orderList = orderRepository.findAllByUserIdOrderByCreatedDateDesc(userId);
        return orderList;
    }

}

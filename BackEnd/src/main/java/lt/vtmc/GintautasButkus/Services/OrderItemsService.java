package lt.vtmc.GintautasButkus.Services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.vtmc.GintautasButkus.Models.OrderItem;
import lt.vtmc.GintautasButkus.Repositories.OrderItemRepository;

@Service
@Transactional
public class OrderItemsService {
	
	 @Autowired
	    private OrderItemRepository orderItemsRepository;

	    public void addOrderedProducts(OrderItem orderItem) {
	        orderItemsRepository.save(orderItem);
	    }

}

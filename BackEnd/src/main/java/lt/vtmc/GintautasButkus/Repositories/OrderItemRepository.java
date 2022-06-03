package lt.vtmc.GintautasButkus.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.vtmc.GintautasButkus.Models.OrderItem;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {


}

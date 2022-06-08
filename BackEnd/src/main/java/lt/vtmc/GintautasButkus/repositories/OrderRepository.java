package lt.vtmc.GintautasButkus.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.vtmc.GintautasButkus.models.Order;


@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
	List<Order> findAllByUserId(Long userId);


}

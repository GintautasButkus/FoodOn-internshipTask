package lt.vtmc.GintautasButkus.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.vtmc.GintautasButkus.Models.Cart;
import lt.vtmc.GintautasButkus.Models.User;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findAllByUserOrderByCreatedDateDesc(User user);

    List<Cart> deleteByUser(User user);

}

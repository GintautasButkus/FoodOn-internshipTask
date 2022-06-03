package lt.vtmc.GintautasButkus.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.vtmc.GintautasButkus.Models.Restaurant;


@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}

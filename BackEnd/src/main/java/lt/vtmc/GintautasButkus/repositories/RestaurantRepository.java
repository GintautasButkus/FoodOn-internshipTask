package lt.vtmc.GintautasButkus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.vtmc.GintautasButkus.models.Restaurant;


@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}

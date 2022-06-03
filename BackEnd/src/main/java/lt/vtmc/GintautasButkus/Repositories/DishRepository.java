package lt.vtmc.GintautasButkus.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.vtmc.GintautasButkus.Models.Dish;



@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {


}
package lt.vtmc.GintautasButkus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.vtmc.GintautasButkus.models.Menu;



@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

}

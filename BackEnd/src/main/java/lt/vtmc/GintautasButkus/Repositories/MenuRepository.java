package lt.vtmc.GintautasButkus.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.vtmc.GintautasButkus.Models.Menu;



@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

}

package lt.vtmc.GintautasButkus.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.vtmc.GintautasButkus.models.ERole;
import lt.vtmc.GintautasButkus.models.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);


}

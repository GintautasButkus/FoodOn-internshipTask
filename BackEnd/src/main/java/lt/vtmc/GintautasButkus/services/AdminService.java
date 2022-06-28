package lt.vtmc.GintautasButkus.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import lt.vtmc.GintautasButkus.exceptions.NoUserExistsException;
import lt.vtmc.GintautasButkus.models.ERole;
import lt.vtmc.GintautasButkus.models.Restaurant;
import lt.vtmc.GintautasButkus.models.Role;
import lt.vtmc.GintautasButkus.models.User;
import lt.vtmc.GintautasButkus.payloadRequest.SignupRequest;
import lt.vtmc.GintautasButkus.payloadResponse.MessageResponse;
import lt.vtmc.GintautasButkus.repositories.DishRepository;
import lt.vtmc.GintautasButkus.repositories.MenuRepository;
import lt.vtmc.GintautasButkus.repositories.RestaurantRepository;
import lt.vtmc.GintautasButkus.repositories.RoleRepository;
import lt.vtmc.GintautasButkus.repositories.UserRepository;

@Service
public class AdminService {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	RestaurantRepository restaurantRepository;

	@Autowired
	MenuRepository menuRepository;

	@Autowired
	DishRepository dishRepository;

// ************************* CREATE ADMIN USER WHEN APP STARTS ***********************
	@PostConstruct
	public ResponseEntity<?> init() {
		User admin = new User("admin", "admin@foodon.lt", encoder.encode("admin"));
		Set<Role> roles = new HashSet<>();
		roles.add(new Role(ERole.ROLE_ADMIN));
		roles.add(new Role(ERole.ROLE_USER));
		admin.setRoles(roles);
		userRepository.save(admin);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

//	******************** REGISTER NEW USER **********************************
	public ResponseEntity<?> registerUserOrAdmin(SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));
		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();
		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;

				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}
		user.setRoles(roles);
		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

//	**************** DELETE USER ******************************
	public void deleteUser(Long id) {
		if (userRepository.existsById(id)) {
			Set<Role> emptyRoles = new HashSet<Role>();
			userRepository.findAll().stream().filter(user -> user.getId() == id).findFirst().get().setRoles(emptyRoles);
			userRepository.deleteById(id);
		} else {
			throw new NoUserExistsException("Sorry, there is no such user.");
		}
	}
	
//	***************** ALL RESTAURANTS ***********************************
	public List<Restaurant> getAllRestaurants(){
		return restaurantRepository.findAll();
	}

}

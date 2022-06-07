package lt.vtmc.GintautasButkus.Services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lt.vtmc.GintautasButkus.Exceptions.NoMenuExistsException;
import lt.vtmc.GintautasButkus.Exceptions.NoRestaurantExistsException;
import lt.vtmc.GintautasButkus.Models.ERole;
import lt.vtmc.GintautasButkus.Models.Menu;
import lt.vtmc.GintautasButkus.Models.Restaurant;
import lt.vtmc.GintautasButkus.Models.Role;
import lt.vtmc.GintautasButkus.Models.User;
import lt.vtmc.GintautasButkus.PayloadRequest.LoginRequest;
import lt.vtmc.GintautasButkus.PayloadRequest.SignupRequest;
import lt.vtmc.GintautasButkus.PayloadResponse.MessageResponse;
import lt.vtmc.GintautasButkus.PayloadResponse.UserInfoResponse;
import lt.vtmc.GintautasButkus.Repositories.DishRepository;
import lt.vtmc.GintautasButkus.Repositories.MenuRepository;
import lt.vtmc.GintautasButkus.Repositories.OrderItemRepository;
import lt.vtmc.GintautasButkus.Repositories.OrderRepository;
import lt.vtmc.GintautasButkus.Repositories.RestaurantRepository;
import lt.vtmc.GintautasButkus.Repositories.RoleRepository;
import lt.vtmc.GintautasButkus.Repositories.UserRepository;
import lt.vtmc.GintautasButkus.Security.Services.UserDetailsImpl;
import lt.vtmc.GintautasButkus.Security.JWT.JwtUtils;



@Service
public class UserService {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired
	MenuRepository menuRepository;
	
	@Autowired
	DishRepository dishRepository;
	
	@Autowired
	OrderItemRepository orderItemRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	
	
//	********************** REGISTER NEW USER [NEW ADMIN REGISTRY DISALLOWED, ONLY USER ***************************************
	public ResponseEntity<?> registerUser(SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}
		if (userRepository.existsByEmail(signUpRequest.getEmail()).booleanValue()) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));
		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();
		if (strRoles == null || signUpRequest.getRole().contains("admin")) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "":
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);

					break;
				

				default:
					Role userDefaultRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userDefaultRole);
				}
			});
		}
		user.setRoles(roles);
		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}


//	*********************** LOGIN ************************************************

	public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body(
				new UserInfoResponse(userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
	}

	public ResponseEntity<?> logoutUser() {
		ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
				.body(new MessageResponse("You've been signed out!"));
	}
	
//	****************** SEARCH RESTAURANT *******************************************
	
	public List<Restaurant> getRestaurantsByName(String name) {
		if (restaurantRepository.findAll().stream().filter(restaurant -> restaurant.getRestaurantName().contains(name)).findFirst().isPresent()) {
			return restaurantRepository.findAll().stream().filter(restaurant -> restaurant.getRestaurantName().contains(name)).collect(Collectors.toList());
		} else {
			throw new NoRestaurantExistsException("No restaurant exists with name " + name);
		}
	}
	
//	****************** GET RESTAURANT *******************************************
	
	public Restaurant getRestaurantsById(Long id) {
		if (restaurantRepository.findById(id).isPresent()) {
			return restaurantRepository.findById(id).get();
		} else {
			throw new NoRestaurantExistsException("No restaurant exists with ID " + id);
		}
	}
	
//	****************** GET RESTAURANT's MENUs *******************************************
	
	public List<Menu> getMenu(Long id) {
		if (restaurantRepository.findById(id).isPresent()) {
			return menuRepository.findAll().stream().filter(menu -> menu.getRestaurant().getId() == id).collect(Collectors.toList());
		} else {
			throw new NoMenuExistsException("No menu exists in restaurant with id " + id);
		}
	}
	
//	******************* GET LOGGED-in USER ID ***********************************************
	public String getUsername() {
		String username = null;
		Object authentication = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (authentication instanceof UserDetails) {
			username = ((UserDetails) authentication).getUsername();
		} else {
			username = authentication.toString();
		}
		return username;
	}
}

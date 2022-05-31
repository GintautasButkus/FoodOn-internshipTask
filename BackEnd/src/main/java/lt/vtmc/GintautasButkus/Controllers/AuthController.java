package lt.vtmc.GintautasButkus.Controllers;


import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import lt.vtmc.GintautasButkus.PayloadRequest.LoginRequest;
import lt.vtmc.GintautasButkus.PayloadRequest.SignupRequest;
import lt.vtmc.GintautasButkus.Services.AdminService;
import lt.vtmc.GintautasButkus.Services.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@Api(value = "", tags = { "New User Registry / Login / Logout" })
@Tag(name = "New User Registry / Login / Logout", description = "Ignitis Users")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	AdminService adminService;
	
	
	
	@PostMapping("/signin")
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest signUpRequest) {
		return userService.authenticateUser(signUpRequest);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/signup")
	public ResponseEntity<?> createUser(@Valid @RequestBody SignupRequest signUpRequest) {
		return adminService.registerUser(signUpRequest);
	}



	@PostMapping("/signout")
	public ResponseEntity<?> logout() {
		return userService.logoutUser();
	}
	
}
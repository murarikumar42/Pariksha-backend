package infy.parksha.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import infy.parksha.commons.UserConstant;
import infy.parksha.entity.User;
import infy.parksha.repository.UserRepository;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@PostMapping("/register")
	public String register(@RequestBody User user) {
		user.setRoles(UserConstant.DEFAULT_ROLE);//USER
		String encryptedPwd= passwordEncoder.encode(user.getPassword());
		user.setPassword(encryptedPwd);
		userRepository.save(user);
		return "Hi "+user.getFirstName()+" welcome Pariksha Quiz Portal";
	}
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping("/access/{userId}/{userRole}")
	public String giveAccessToUser(@PathVariable int userId,@PathVariable String userRole,Principal principal) {
		User user=userRepository.findById(userId).get();
		List<String> activeRoles=getRolesByLoggedInUser(principal);
		
		if(activeRoles.contains(userRole)) {
			user.setRoles(userRole);
		}
		userRepository.save(user);
		return "HI "+user.getFirstName()+" New roles assign to you by "+principal.getName();
	}
	
	@GetMapping
	@Secured("ROLE_ADMIN")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<User> loadUsers(){
		return userRepository.findAll();
	}
	
	@GetMapping("/test")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String testUserAccess() {
		return "User can only access this";
	}
	
	private List<String> getRolesByLoggedInUser(Principal principal){
		String roles=getLoggedInUser(principal).getRoles();
		List<String> assignRoles=Arrays.stream(roles.split(",")).collect(Collectors.toList());
		if(assignRoles.contains("ROLE_ADMIN")) {
			return Arrays.stream(UserConstant.ADMIN_ACCESS).collect(Collectors.toList());
		}
		
		return Collections.emptyList();
		
	}
	private User getLoggedInUser(Principal principal) {
		return userRepository.findByEmailId(principal.getName()).get();
	}
	
	
}

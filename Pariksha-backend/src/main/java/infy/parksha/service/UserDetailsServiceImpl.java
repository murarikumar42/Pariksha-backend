package infy.parksha.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import infy.parksha.dto.UserSecurityDetails;
import infy.parksha.entity.User;
import infy.parksha.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
		Optional<User> user = userRepo.findByEmailId(emailId);
		return user.map(UserSecurityDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException(emailId + "doesn't exist "));
	}

}

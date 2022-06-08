package lt.vtmc.GintautasButkus.security.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lt.vtmc.GintautasButkus.models.User;
import lt.vtmc.GintautasButkus.repositories.UserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserRepository userRepository;
  
  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
    return UserDetailsImpl.build(user);
  }
}


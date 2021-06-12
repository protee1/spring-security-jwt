package com.proteech.login.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proteech.login.model.User;
import com.proteech.login.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
@Autowired
private UserRepository userRepository;
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User user=new User();
try {
	user = (User) userRepository.findByUsername(username)
	.orElseThrow(()-> new UsernameNotFoundException("User not found with -> username or email :" + username));
} catch (Throwable e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

		return UserPrinciple.build(user);
	}

}

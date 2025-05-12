package com.devmonk.deliveryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devmonk.model.Role;
import com.devmonk.model.User;
import com.devmonk.repository.RoleRepository;
import com.devmonk.repository.UserRepository;

@Service
public class DeliveryService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;

	
	@Transactional
	public void saveUserWithRole(User user, String roleName, String siteURL) throws Exception {
	    String encodedPassword = passwordEncoder.encode(user.getPassword());
	    user.setPassword(encodedPassword);

	    // Fetch or create Role
	    Role role = roleRepository.findByName(roleName);
	    if (role == null) {
	        role = new Role();
	        role.setName(roleName);
	        roleRepository.save(role);
	    }

	    // Add role to user's roles set
	    user.getRoles().add(role);

	    userRepository.save(user);
	}

}

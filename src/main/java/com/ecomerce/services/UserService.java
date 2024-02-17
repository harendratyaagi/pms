package com.ecomerce.services;

import com.ecomerce.model.Role;
import com.ecomerce.model.User;
import com.ecomerce.repository.RoleRepository;
import com.ecomerce.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role adminRole = roleRepository.findByName("ROLE_ADMIN").orElse(null);
        user.setRoles(Arrays.asList(adminRole));
        return userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User get(UUID uuid) {
        return userRepository.findById(uuid).orElseThrow(() -> new RuntimeException("User fetching failed"));
    }
}

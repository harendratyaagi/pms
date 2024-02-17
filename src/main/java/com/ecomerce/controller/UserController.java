package com.ecomerce.controller;

import com.ecomerce.model.User;
import com.ecomerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome on pms site";
    }


    @PostMapping("/create")
   // @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{uuid}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public User get(@PathVariable UUID uuid) {
        //logged in user 
        return userService.get(uuid);
    }
}

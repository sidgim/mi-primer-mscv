package org.glara.springcloud.msvc.users.controllers;

import jakarta.validation.Valid;
import org.glara.springcloud.msvc.exception.Validations;
import org.glara.springcloud.msvc.users.models.entity.User;
import org.glara.springcloud.msvc.users.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public List<User> listUser() {
        return userService.listUser();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> findUserById(@PathVariable UUID id) {
        User user = userService.findUserById(id);
        return ResponseEntity.ok(user);

    }

    @PostMapping("/user")
    public ResponseEntity<?> saveUser(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            return Validations.validateBindingResult(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user));
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> updateUser(@Valid @RequestBody User user, @PathVariable UUID id, BindingResult result) {
        if (result.hasErrors()) {
            return Validations.validateBindingResult(result);
        }
        return  ResponseEntity.ok(userService.updateUser(id, user));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<User> deleteUseByIdr(@PathVariable UUID id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

}

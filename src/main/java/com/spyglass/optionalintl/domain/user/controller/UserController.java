package com.spyglass.optionalintl.domain.user.controller;

import com.spyglass.optionalintl.domain.user.exception.UserNotFoundException;
import com.spyglass.optionalintl.domain.user.model.User;
import com.spyglass.optionalintl.domain.user.repo.UserRepo;
import com.spyglass.optionalintl.domain.user.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

    private UserService userService;
    private UserRepo userRepo;

    @Autowired
    public UserController(UserService userService) throws UserNotFoundException {
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<User> create(@RequestBody User user) {
        User savedUser = userService.create(user);
        ResponseEntity response = new ResponseEntity(savedUser, HttpStatus.CREATED);
        return response;
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getAllWidgets() throws UserNotFoundException {
        Iterable<User> widgets = userService.findAll();
        ResponseEntity<List<User>> response = new ResponseEntity(widgets, HttpStatus.OK);
        return response;
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<User>> getAll() throws UserNotFoundException {
        Iterable<User> all = userService.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> requestUser(@PathVariable Long id) throws UserNotFoundException {
        User response = userService.findById(id);
        log.info(response.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProfile(@PathVariable Long id) {
        try {
            userService.delete(id);
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        } catch (UserNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }
}

package com.spyglass.optionalintl.domain.user.controller;

import com.spyglass.optionalintl.domain.user.exception.UserNotFoundException;
import com.spyglass.optionalintl.domain.user.model.User;
import com.spyglass.optionalintl.domain.user.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
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

   @PostMapping("")
    public ResponseEntity<User> create(@RequestBody User user){
        user = userService.create(user);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
   }
}

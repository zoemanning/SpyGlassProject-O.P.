package com.spyglass.optionalintl.domain.user.services;

import com.spyglass.optionalintl.domain.user.exception.UserNotFoundException;
import com.spyglass.optionalintl.domain.user.model.User;

import java.util.List;


public interface UserService {
    User create(User user);
    User findById(Long id) throws UserNotFoundException;
    List<User> findAll()throws UserNotFoundException;
    Boolean delete(Long id)throws UserNotFoundException;
}

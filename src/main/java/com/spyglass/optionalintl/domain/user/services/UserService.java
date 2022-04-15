package com.spyglass.optionalintl.domain.user.services;

import com.spyglass.optionalintl.domain.user.exception.UserNotFoundException;
import com.spyglass.optionalintl.domain.user.model.User;


public interface UserService {
    User create(User user);
    User findById(Long id) throws UserNotFoundException;
    Iterable<User> findAll()throws UserNotFoundException;
    void delete(Long id)throws UserNotFoundException;
}

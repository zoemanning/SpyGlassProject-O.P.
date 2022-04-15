package com.spyglass.optionalintl.domain.user.services;

import com.spyglass.optionalintl.domain.user.exception.UserNotFoundException;
import com.spyglass.optionalintl.domain.user.model.User;
import com.spyglass.optionalintl.domain.user.repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private UserService userService;
    private UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User create(User user) {
        user.setId(user.getId());
        return userRepo.save(user);
    }

    @Override
    public User findById(Long id) throws UserNotFoundException {
        Optional<User> userOptional = userRepo.findById(id);
        if(userOptional.isEmpty())
            throw new UserNotFoundException("User not found");
        return userOptional.get();
    }

    @Override
    public Iterable<User> findAll() throws UserNotFoundException{
        return userRepo.findAll();
    }

    @Override
    public void delete(Long id) throws UserNotFoundException{
        Optional<User> userOptional = userRepo.findById(id);
        if(userOptional.isEmpty())
            throw  new UserNotFoundException("User not found");
        User userToRemove = userOptional.get();
        userRepo.delete(userToRemove);
    }
}

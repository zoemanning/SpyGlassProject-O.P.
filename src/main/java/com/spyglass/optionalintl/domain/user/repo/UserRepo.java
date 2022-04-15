package com.spyglass.optionalintl.domain.user.repo;

import com.spyglass.optionalintl.domain.user.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepo extends CrudRepository<User,Long> {
    Optional<User> findByLastName(String lastName);
}

package com.spyglass.optionalintl.domain.goal.repo;

import com.spyglass.optionalintl.domain.goal.model.Goal;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GoalRepo extends CrudRepository <Goal, Long> {
    Optional<Goal> findByTitle(String title);

}

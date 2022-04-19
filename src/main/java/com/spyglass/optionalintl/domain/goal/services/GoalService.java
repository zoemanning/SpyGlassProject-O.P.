package com.spyglass.optionalintl.domain.goal.services;

import com.spyglass.optionalintl.domain.goal.exception.GoalNotFoundException;
import com.spyglass.optionalintl.domain.goal.model.Goal;
import com.spyglass.optionalintl.domain.goal.model.goalType;

import java.util.List;


public interface GoalService {
    Goal create (Goal goal);
    Boolean delete (Long id) throws GoalNotFoundException;
    Boolean deleteGoalByTitle (String title) throws GoalNotFoundException;
    Goal update (Goal goal) throws GoalNotFoundException;
    Goal findById (Long id) throws GoalNotFoundException;
    Goal findByTitle (String title) throws GoalNotFoundException;
    Goal findByGoalType (goalType goalType ) throws GoalNotFoundException;
    List<Goal> findAll();
}

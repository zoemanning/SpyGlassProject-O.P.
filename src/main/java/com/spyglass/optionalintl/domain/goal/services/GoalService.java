package com.spyglass.optionalintl.domain.goal.services;

import com.spyglass.optionalintl.domain.goal.exception.GoalNotFoundException;
import com.spyglass.optionalintl.domain.goal.model.Goal;


public interface GoalService {
    Goal create (Goal goal);
    void delete (Long id) throws GoalNotFoundException;
    Goal update (Goal goal) throws GoalNotFoundException;
    Goal findById (Long id) throws GoalNotFoundException;
    Goal findByTitle (String title) throws GoalNotFoundException;
    Double calculatePercentage(Double targetSavingsAmount, Double amountSaved);
    String milestoneMessage(String message, Double targetSavingsAmount, Double amountSaved);
    Iterable<Goal> findAll();
}

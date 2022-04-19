package com.spyglass.optionalintl.domain.goal.services;

import com.spyglass.optionalintl.domain.goal.exception.GoalNotFoundException;

import com.spyglass.optionalintl.domain.goal.model.Goal;
import com.spyglass.optionalintl.domain.goal.model.goalType;
import com.spyglass.optionalintl.domain.goal.repo.GoalRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class GoalServiceImpl implements GoalService {

    private static final Logger logger = LoggerFactory.getLogger(GoalServiceImpl.class);
    private GoalRepo goalRepo;

    @Autowired
    public GoalServiceImpl(GoalRepo goalRepo) {
        this.goalRepo = goalRepo;
    }

    @Override
    public Goal create(Goal goal) {
        calculatePercentage(goal);
        milestoneMessage(goal);
        return goalRepo.save(goal);
    }

    @Override
    public Goal findById(Long id) throws GoalNotFoundException {
        Optional<Goal> goalOptional = goalRepo.findById(id);
        if (goalOptional.isEmpty())
            throw new GoalNotFoundException("Goal not found");
        return goalOptional.get();
    }

    @Override
    public Goal findByTitle(String title) throws GoalNotFoundException {
        Optional<Goal> goalOptional = goalRepo.findByTitle("Going to Hawaii");
        if (goalOptional.isEmpty())
            throw new GoalNotFoundException("Goal not found");
        return goalOptional.get();
    }

    @Override
    public Goal findByGoalType(goalType goalType) throws GoalNotFoundException {
        Optional<Goal> goalOptional = goalRepo.findByGoalType(goalType);
        if (goalOptional.isEmpty())
            throw new GoalNotFoundException("Goal not found");
        return goalOptional.get();
    }

    @Override
    public Goal update(Goal goal) throws GoalNotFoundException {
        Long id = goal.getId();
        Optional<Goal> goalExistOption = goalRepo.findById(id);
        if (goalExistOption.isEmpty()) {
            throw new GoalNotFoundException("Goal not found");}
        Goal updatedGoal = goalExistOption.get();
        calculatePercentage(updatedGoal);
        milestoneMessage(updatedGoal);
        return goalRepo.save(goal);
    }

    @Override
    public List<Goal> findAll() {
        return (List<Goal>) goalRepo.findAll();
    }

    @Override
    public Boolean delete(Long id) throws GoalNotFoundException {
        Optional<Goal> goalOptional = goalRepo.findById(id);
        if (goalOptional.isEmpty())
            throw new GoalNotFoundException("Goal not found");
        Goal goalToRemove = goalOptional.get();
        goalRepo.delete(goalToRemove);

        return true;
    }

    @Override
    public Boolean deleteGoalByTitle (String title) throws GoalNotFoundException {
        Optional<Goal> goalOptional = goalRepo.findByTitle(title);
        if (goalOptional.isEmpty())
            throw new GoalNotFoundException ("Goal does not exist, cannot delete goal");

        Goal goalToDeleteByTitle = goalOptional.get();
        goalRepo.delete(goalToDeleteByTitle);

        return true;
    }

    private static void calculatePercentage(Goal goal) {
        Double amountSaved = goal.getAmountSaved();
        Double targetSavingsAmount = goal.getTargetSavingsAmount();
        Double percentageCalculation = (amountSaved / targetSavingsAmount) * 100;
        goal.setProgressPercentage(percentageCalculation);

        Double amountLeftToGoal = targetSavingsAmount - amountSaved;
        goal.setProgressAmount(amountLeftToGoal);
        logger.info("You have reached your goal");

    }

    private static String milestoneMessage(Goal goal) {
        String message = "";
        Double amountSaved = goal.getAmountSaved();
        Double targetSavingsAmount = goal.getTargetSavingsAmount();

        if (amountSaved/targetSavingsAmount == 25) {
            message = "Congratulations you are 75% away from your goal";
        }

        else if (amountSaved/targetSavingsAmount  == 50){
            message = "Congratulations you are 50% away from your goal";
        }
        else if (amountSaved/targetSavingsAmount == 75){
            message = "Congratulations you are 25% away from your goal";
        }
        else if (amountSaved/targetSavingsAmount == 100){
            message = "Congratulations you successful made it to your target goal" + goal.getTargetSavingsAmount();
        }


        return message;
    }

}




package com.spyglass.optionalintl.goal.services;

import com.spyglass.optionalintl.domain.goal.exception.GoalNotFoundException;
import com.spyglass.optionalintl.domain.goal.model.Goal;
import com.spyglass.optionalintl.domain.goal.model.goalType;
import com.spyglass.optionalintl.domain.goal.repo.GoalRepo;
import com.spyglass.optionalintl.domain.goal.services.GoalService;
import com.spyglass.optionalintl.domain.user.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class GoalServiceImplTest {

    @MockBean
    private GoalRepo goalRepo;

    @Autowired
    private GoalService goalService;

    private Goal input;
    private Goal output;
    private User user;
    SimpleDateFormat targetSavingsDate = new SimpleDateFormat("MM/DD/YYYY");

    @BeforeEach
    public void setUp() throws ParseException {

        targetSavingsDate.parse("02/22/2028");

        input = new Goal("Going to Hawaii", 3000.00, 600.00, targetSavingsDate, "notes", goalType.VACATION_GOAL);
        output = new Goal("Going to Hawaii", 3000.00, 600.00, targetSavingsDate, "note", goalType.VACATION_GOAL);
        output.setId(1l);
        output.setTitle("Going to Hawaii");

    }

    @Test
    @DisplayName("Create Goal")
    public void createGoalTest01() {
        BDDMockito.doReturn(output).when(goalRepo).save(ArgumentMatchers.any());
        Goal returnedGoal = goalService.create(input);
        Assertions.assertNotNull(output);
        Assertions.assertEquals(returnedGoal.getId(), 1L);
    }

    @Test
    @DisplayName("Find goal  by title - success")
    public void findByTitleTest01() throws GoalNotFoundException {

        BDDMockito.doReturn(Optional.of(input)).when(goalRepo).findByTitle("Going to Hawaii");
        Goal foundGoal = goalService.findByTitle("Going to Hawaii");
        Assertions.assertEquals(input.toString(),foundGoal.toString());
    }

    @Test
    @DisplayName("Find goal by Id - success")
    public void findByIdTest02() throws GoalNotFoundException {

        BDDMockito.doReturn(Optional.of(output)).when(goalRepo).findById(1l);
        Goal foundGoal = goalService.findById(1l);
        Assertions.assertEquals(output.toString(),foundGoal.toString());
    }

    @Test
    @DisplayName("Find goal by Goal Type - success")
    public void findByIdTest03() throws GoalNotFoundException {

        BDDMockito.doReturn(Optional.of(output)).when(goalRepo).findByGoalType(goalType.VACATION_GOAL);
        Goal foundGoal = goalService.findByGoalType(goalType.VACATION_GOAL);
        Assertions.assertEquals(output.toString(),foundGoal.toString());
    }

    @Test
    @DisplayName("Find goal by Id - Fail")
    public void findByIdTest01Failed(){
        BDDMockito.doReturn(Optional.empty()).when(goalRepo).findById(1L);
        Assertions.assertThrows(GoalNotFoundException.class, () ->{
            goalService.findById(1L);
        });
    }

    @Test
    @DisplayName("Find goal by title - Fail")
    public void findByIdTest02Failed(){
        BDDMockito.doReturn(Optional.empty()).when(goalRepo).findByTitle("Going to Hawaii");
        Assertions.assertThrows(GoalNotFoundException.class, () -> {
            goalService.findByTitle("Going to Hawaii");
        });
    }

    @Test
    @DisplayName("Find goal by goal type - Fail")
    public void findByIdTest03Failed(){
        BDDMockito.doReturn(Optional.empty()).when(goalRepo).findByGoalType(goalType.VACATION_GOAL);
        Assertions.assertThrows(GoalNotFoundException.class, () -> {
            goalService.findByGoalType(goalType.VACATION_GOAL);
        });
    }

    @Test
    @DisplayName("Update Goal - Success")
    public void updateGoalTest01() throws GoalNotFoundException, ParseException {

        Goal expectedGoalUpdate = new Goal("Going to Hawaii", 3000.00, 600.00, targetSavingsDate, "notes", goalType.VACATION_GOAL);
        expectedGoalUpdate.setId(1L);
        BDDMockito.doReturn(Optional.of(input)).when(goalRepo).findById(1L);
        BDDMockito.doReturn(expectedGoalUpdate).when(goalRepo).save(ArgumentMatchers.any());
        Goal actualGoal = goalService.update(expectedGoalUpdate);
        Assertions.assertEquals(expectedGoalUpdate.toString(), actualGoal.toString());
    }

    @Test
    @DisplayName("Goal Service: Update Goal - Fail")
    public void updateGoalTestFail() throws ParseException {

       Goal expectedGoalUpdate = new Goal("Going to Hawaii", 3000.00, 600.00, targetSavingsDate, "notes", goalType.VACATION_GOAL);
       expectedGoalUpdate.setId(1L);
       BDDMockito.doReturn(Optional.empty()).when(goalRepo).findById(1L);
       Assertions.assertThrows(GoalNotFoundException.class, ()-> {
           goalService.update(expectedGoalUpdate);
       });
    }


    @Test
    @DisplayName("Delete Goal")
    public void deleteGoalTest01() {
        BDDMockito.doReturn(Optional.empty()).when(goalRepo).findById(1l);
        Assertions.assertThrows(GoalNotFoundException.class, () -> {
            goalService.delete(1l);
        });
    }

    @Test
    @DisplayName("Delete Goal by Title")
    public void deleteGoalTestByTitle01() {
        BDDMockito.doReturn(Optional.empty()).when(goalRepo).findByTitle("Going to Hawaii");
        Assertions.assertThrows(GoalNotFoundException.class, () -> {
            goalService.delete(1l);

        });
    }

    @Test
    @DisplayName("Calculate Progress Test - Success")
    public void calculateProgressBarTestSuccess01(){
        BDDMockito.doReturn(input).when(goalRepo).save(ArgumentMatchers.any());
        Goal createdGoal = goalService.create(input);
        Assertions.assertNotNull(createdGoal);

        Double expected = 20.00;
        Double actual = createdGoal.getProgressPercentage();
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Calculate Progress (Amount left) Test - Success")
    public void calculateProgressBarTestSuccess02(){
        BDDMockito.doReturn(input).when(goalRepo).save(ArgumentMatchers.any());
        Goal createdGoal = goalService.create(input);
        Assertions.assertNotNull(createdGoal);

        Double expected = 2400.00;
        Double actual = createdGoal.getProgressAmount();
        Assertions.assertEquals(expected,actual);
    }

}








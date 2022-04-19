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
import java.util.Date;
import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class GoalServiceImplTest {

    @MockBean
    private GoalRepo goalRepo;

    @Autowired
    private GoalService goalService;

    private Goal mockGoal1;
    private Goal mockGoal2;
    private Goal inputGoal;
    SimpleDateFormat formatter = new SimpleDateFormat("MM/DD/YYYY");
    Date targetDate;

    @BeforeEach
    public void setUp() throws ParseException {

        targetDate = formatter.parse("02/22/2028");

        inputGoal = new Goal("Going to Hawaii", 3000.00, 600.00, targetDate, "notes", goalType.VACATION_GOAL);

        mockGoal1 = new Goal("Going to Hawaii", 3000.00, 600.00, targetDate, "notes", goalType.VACATION_GOAL);
        mockGoal2 = new Goal("Going to London", 3000.00, 600.00, targetDate, "note", goalType.VACATION_GOAL);

        mockGoal1.setId(1L);
        mockGoal1.setTitle("Going to Hawaii");
        mockGoal2.setId(2L);
        mockGoal1.setTitle("Going to London");

    }

    @Test
    @DisplayName("Create Goal")
    public void createGoalTest01() {
        BDDMockito.doReturn(mockGoal2).when(goalRepo).save(ArgumentMatchers.any());
        Goal returnedGoal = goalService.create(mockGoal1);
        Assertions.assertNotNull(mockGoal2);
        Assertions.assertEquals(returnedGoal.getId(), 2L);
    }

    @Test
    @DisplayName("Find goal  by title - success")
    public void findByTitleTest01() throws GoalNotFoundException {

        BDDMockito.doReturn(Optional.of(mockGoal1)).when(goalRepo).findByTitle("Going to Hawaii");
        Goal foundGoal = goalService.findByTitle("Going to Hawaii");
        Assertions.assertEquals(mockGoal1.toString(),foundGoal.toString());
    }

    @Test
    @DisplayName("Find goal by Id - success")
    public void findByIdTest02() throws GoalNotFoundException {

        BDDMockito.doReturn(Optional.of(mockGoal2)).when(goalRepo).findById(1l);
        Goal foundGoal = goalService.findById(1l);
        Assertions.assertEquals(mockGoal2.toString(),foundGoal.toString());
    }

    @Test
    @DisplayName("Find goal by Goal Type - success")
    public void findByIdTest03() throws GoalNotFoundException {

        BDDMockito.doReturn(Optional.of(mockGoal2)).when(goalRepo).findByGoalType(goalType.VACATION_GOAL);
        Goal foundGoal = goalService.findByGoalType(goalType.VACATION_GOAL);
        Assertions.assertEquals(mockGoal2.toString(),foundGoal.toString());
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

        Goal expectedGoalUpdate = new Goal("Going to Hawaii", 3000.00, 600.00, targetDate, "notes", goalType.VACATION_GOAL);
        expectedGoalUpdate.setId(1L);
        BDDMockito.doReturn(Optional.of(mockGoal1)).when(goalRepo).findById(1L);
        BDDMockito.doReturn(expectedGoalUpdate).when(goalRepo).save(ArgumentMatchers.any());
        Goal actualGoal = goalService.update(expectedGoalUpdate);
        Assertions.assertEquals(expectedGoalUpdate.toString(), actualGoal.toString());
    }

    @Test
    @DisplayName("Goal Service: Update Goal - Fail")
    public void updateGoalTestFail() throws ParseException {

       Goal expectedGoalUpdate = new Goal("Going to Hawaii", 3000.00, 600.00, targetDate, "notes", goalType.VACATION_GOAL);
       expectedGoalUpdate.setId(1L);
       BDDMockito.doReturn(Optional.empty()).when(goalRepo).findById(1L);
       Assertions.assertThrows(GoalNotFoundException.class, ()-> {
           goalService.update(expectedGoalUpdate);
       });
    }

    @Test
    @DisplayName("Goal Service: Delete Goal - Success")
    public void deleteGoalTestSuccess01() throws GoalNotFoundException {
        BDDMockito.doReturn(Optional.of(mockGoal1)).when(goalRepo).findById(1L);
        Boolean actualResponse = goalService.delete(1L);
        Assertions.assertTrue(actualResponse);

    }

    @Test
    @DisplayName("Goal Service: Delete Goal - Fail")
    public void deleteGoalTestFail01(){
        BDDMockito.doReturn(Optional.empty()).when(goalRepo).findById(1L);
        Assertions.assertThrows(GoalNotFoundException.class, ()-> {
            goalService.delete(1L);
        });
    }


    @Test
    @DisplayName("Goal Service: Delete Goal by Title - Success")
    public void deleteGoalTestByTitle01() throws GoalNotFoundException {
        BDDMockito.doReturn(Optional.of(mockGoal2)).when(goalRepo).findByTitle("Going to London");
        Boolean actualResponse = goalService.deleteGoalByTitle("Going to London");
        Assertions.assertTrue(actualResponse);

    }

    @Test
    @DisplayName("Goal Service: Delete Goal - Fail")
    public void deleteGoalTestByTitleFail01(){
        BDDMockito.doReturn(Optional.empty()).when(goalRepo).findByTitle("Going to London");
        Assertions.assertThrows(GoalNotFoundException.class, ()-> {
            goalService.deleteGoalByTitle("Going to London");
        });
    }

    @Test
    @DisplayName("Calculate Progress Test - Success")
    public void calculateProgressBarTestSuccess01(){
        BDDMockito.doReturn(mockGoal1).when(goalRepo).save(ArgumentMatchers.any());
        Goal createdGoal = goalService.create(mockGoal1);
        Assertions.assertNotNull(createdGoal);

        Double expected = 20.00;
        Double actual = createdGoal.getProgressPercentage();
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Calculate Progress (Amount left) Test - Success")
    public void calculateProgressBarTestSuccess02(){
        BDDMockito.doReturn(mockGoal1).when(goalRepo).save(ArgumentMatchers.any());
        Goal createdGoal = goalService.create(mockGoal1);
        Assertions.assertNotNull(createdGoal);

        Double expected = 2400.00;
        Double actual = createdGoal.getProgressAmount();
        Assertions.assertEquals(expected,actual);
    }

}








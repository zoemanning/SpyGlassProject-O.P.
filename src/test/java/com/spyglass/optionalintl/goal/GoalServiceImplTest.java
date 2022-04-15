package com.spyglass.optionalintl.goal;

import com.spyglass.optionalintl.domain.goal.exception.GoalNotFoundException;
import com.spyglass.optionalintl.domain.goal.model.Goal;
import com.spyglass.optionalintl.domain.goal.model.goalType;
import com.spyglass.optionalintl.domain.goal.repo.GoalRepo;
import com.spyglass.optionalintl.domain.goal.services.GoalService;
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

    @BeforeEach
    public void setUp() throws ParseException {
        SimpleDateFormat dateOfBirth01 = new SimpleDateFormat("MM/DD/YYY");

        input = new Goal("Going to Hawaii", 3000.00, 520.00, dateOfBirth01.parse("07/04/2022"), "notes", 17.0, goalType.VACATION_GOAL);
        output = new Goal("Going to Hawaii", 3000.00, 520.00, dateOfBirth01.parse("07/04/2022"), "notes", 17.0, goalType.VACATION_GOAL);
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
    @DisplayName("Update Goal")
    public void updateGoalTest01() throws GoalNotFoundException {

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
    @DisplayName("Calculate progress")
    public void calculatePercentageTest01() {

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
    public void findByIdTest01() throws GoalNotFoundException {

        BDDMockito.doReturn(Optional.of(output)).when(goalRepo).findById(1l);
        Goal foundGoal = goalService.findById(1l);
        Assertions.assertEquals(output.toString(),foundGoal.toString());
    }
}








package com.spyglass.optionalintl.goal.models;

import com.spyglass.optionalintl.domain.goal.model.Goal;
import com.spyglass.optionalintl.domain.goal.model.goalType;
import com.spyglass.optionalintl.domain.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GoalTest {

    private Goal goalOne;
    private Goal goalTwo;
    private User user;

    private Goal emptyGoalOne;
    private Goal emptyGoalTwo;

    @BeforeEach
    public void setUp() throws ParseException {
        emptyGoalOne= new Goal();
        emptyGoalTwo = new Goal();

        SimpleDateFormat formatter = new SimpleDateFormat("MM/DD/YYY");
        Date targetDate = formatter.parse("02/22/2028");

        goalOne = new Goal("Going to Hawaii", 3000.00, 600.00, targetDate, "notes", goalType.VACATION_GOAL);
        goalOne.setId(1L);

        goalTwo = new  Goal("Pay Credit Card", 4000.00, 400.00, targetDate, "notes", goalType.PERSONAL_GOAL);
        goalTwo.setId(2L);

    }

    @Test
    public void testEmptyToString() throws Exception {
        assertEquals(
                emptyGoalOne.toString(),
                emptyGoalTwo.toString(),
                "Both empty Goal instances should have the same toString");

    }

    @Test
    public void testContentToString() throws Exception {
        assertNotEquals(
                goalOne.toString(),
                goalTwo.toString(),
                "Both non-empty Goal instances should not have the same toString");

    }

    @Test
    public void testNotToString() throws Exception {
        assertNotEquals(
                emptyGoalOne.toString(),
                goalTwo.toString(),
                "The Goal instances should not have the same toString");

    }
}

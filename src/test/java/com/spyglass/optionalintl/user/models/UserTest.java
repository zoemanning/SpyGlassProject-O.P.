package com.spyglass.optionalintl.user.models;

import com.spyglass.optionalintl.domain.goal.model.Goal;
import com.spyglass.optionalintl.domain.user.model.User;
import org.junit.jupiter.api.BeforeEach;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UserTest {
    private User userOne;
    private User userTwo;
    private Goal goal;

    private User noUser;
    private User noUserTwo;

    @BeforeEach
    public void  setUp () throws ParseException {
        noUser = new User();
        noUserTwo = new User();

        SimpleDateFormat savingsDateGoal = new SimpleDateFormat("MM/DD/YYYY");
//        userOne = new User("Zoe", "Manning", savingsDateGoal.parse("06/14/2023"), "zoe@gmail.com");
    }

}

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

        SimpleDateFormat dateOfBirth = new SimpleDateFormat("MM/DD/YYYY");
        dateOfBirth.parse("02/22/2023");

//        userOne = new User("Dj", "Cottingham", dateOfBirth, "optionalintelligence@gmail.com");


        userOne.setId(1L);

//        userTwo = new User("DJ", "Cottingham", )
    }

}

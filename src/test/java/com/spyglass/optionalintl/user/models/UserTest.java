package com.spyglass.optionalintl.user.models;

import com.spyglass.optionalintl.domain.goal.model.Goal;
import com.spyglass.optionalintl.domain.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UserTest {
    private User userOne;
    private User userTwo;
    private Goal goal;

    private User noUser;
    private User noUserTwo;

    @BeforeEach
    public void setUp() throws ParseException {
        noUser = new User();
        noUserTwo = new User();

        SimpleDateFormat formatter = new SimpleDateFormat("MM/DD/YYYY");
        Date dateOfBirth = formatter.parse("06/14/1992");
        Date dateOfBirth2 = formatter.parse("08/17/1964");

        userOne = new User("Dj", "Cottingham", dateOfBirth, "optionalintelligence@gmail.com");
        userOne.setId(1L);

        userTwo = new User("James", "Bond", dateOfBirth2, "007@paramountmovies.com");
        userTwo.setId(2L);

    }

    @Test
    public void testEmptyToString() throws Exception {
        assertEquals(
                noUser.toString(),
                noUserTwo.toString(),
                "Both empty User instances should have the same toString");

    }

    @Test
    public void testContentToString() throws Exception {
        assertNotEquals(
                userOne.toString(),
                userTwo.toString(),
                "Both non-empty Users instances should not have the same toString");


    }

    @Test
    public void testNotToString() throws Exception {

        assertNotEquals(noUser.toString(),
                userTwo.toString(),
                "The User instances should not have the same toString");


    }

}
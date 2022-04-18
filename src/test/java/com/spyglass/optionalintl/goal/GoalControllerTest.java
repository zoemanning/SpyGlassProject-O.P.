package com.spyglass.optionalintl.goal;

import com.spyglass.optionalintl.domain.goal.controller.GoalController;
import com.spyglass.optionalintl.domain.goal.model.Goal;
import com.spyglass.optionalintl.domain.goal.model.goalType;
import com.spyglass.optionalintl.domain.goal.services.GoalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)

public class GoalControllerTest {


    @Autowired
    private MockMvc mvc;

    private Goal input;
    private Goal firstGoal;


    @MockBean
    private GoalService goalService;


    @BeforeEach
    public void setup () throws ParseException {
        List<Goal> goals = new ArrayList<>();
        SimpleDateFormat dateOfBirth01 = new SimpleDateFormat("MM/DD/YYY");

        goals.add(new Goal("Going to Hawaii", 3000.00, 520.00, dateOfBirth01.parse("07/04/2022"), "notes", 17.0, goalType.VACATION_GOAL));
    }



    @Test
    public void controllerTest01 () throws Exception {
        SimpleDateFormat dateOfBirth01 = new SimpleDateFormat("MM/DD/YYY");
        Goal vacation = new Goal("Going to Hawaii", 3000.00, 520.00, dateOfBirth01.parse("07/04/2022"), "notes", 17.0, goalType.VACATION_GOAL);

        Iterable<Goal> allGoals = Arrays.asList(vacation);

//        BDDMockito.doReturn(Optional.of(goalService.findAll())).doReturn(allGoals);

        given(goalService.findAll()).willReturn(allGoals);


        mvc.perform(get("/" +
                        "goal/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$", hasSize(1)))
                .andExpect((ResultMatcher) jsonPath("$[0].name", is(vacation.getGoalType())));





    }
}

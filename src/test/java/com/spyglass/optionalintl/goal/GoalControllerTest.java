package com.spyglass.optionalintl.goal;

import com.spyglass.optionalintl.domain.goal.model.Goal;
import com.spyglass.optionalintl.domain.goal.model.goalType;
import com.spyglass.optionalintl.domain.goal.services.GoalService;
import com.spyglass.optionalintl.domain.user.model.User;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.spyglass.optionalintl.BaseControllerTest.asJsonString;
import static org.hamcrest.core.Is.is;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)

public class GoalControllerTest {


    @Autowired
    private MockMvc mvc;

    private Goal inputGoal;
    private Goal mockGoal1;
    private Goal mockGoal2;
    private User user;


    @MockBean
    private GoalService goalService;


    @BeforeEach
    public void setup() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/DD/YYY");
        Date targetDate = formatter.parse("02/22/2028");

        user = new User();
        inputGoal = new Goal("Going to Hawaii", 3000.00, 520.00, targetDate, "notes", goalType.VACATION_GOAL);


        mockGoal1 = new Goal("Going to Hawaii", 3000.00, 520.00, targetDate, "notes", goalType.VACATION_GOAL);
        mockGoal1.setId(1L);
        mockGoal2 = new Goal("Pay Credit Card", 4000.00, 600.00, targetDate, "notes", goalType.PERSONAL_GOAL);
        mockGoal2.setId(2L);


    }

    @Test
    @DisplayName("Get/all - success")
    public void getGoalByTitleTestSuccess() throws Exception {


        BDDMockito.doReturn(mockGoal1).when(goalService).findById(1L);

        mvc.perform(get("/goal/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", is(mockGoal1.getTitle())));

    }

    @Test
    @DisplayName("Get /Goal/1 - Not Found")
    public void getGoalByTitleTestFail() throws Exception {

    }

    @Test
    @DisplayName("Create Goal: Success")
    public void createGoalControllerTest01() throws Exception {
        BDDMockito.doReturn(mockGoal1).when(goalService).create(any());

        mvc.perform(MockMvcRequestBuilders.post("/goal")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(inputGoal)))

                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Is.is(mockGoal1.getTitle())));
    }


}
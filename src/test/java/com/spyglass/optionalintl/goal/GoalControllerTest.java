package com.spyglass.optionalintl.goal;

import com.spyglass.optionalintl.domain.goal.exception.GoalNotFoundException;
import com.spyglass.optionalintl.domain.goal.model.Goal;
import com.spyglass.optionalintl.domain.goal.model.goalType;
import com.spyglass.optionalintl.domain.goal.services.GoalService;
import com.spyglass.optionalintl.domain.user.exception.UserNotFoundException;
import com.spyglass.optionalintl.domain.user.model.User;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Assertions;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
        BDDMockito.doThrow(new GoalNotFoundException("Goal Not Found")).when(goalService).findById(1L);
        mvc.perform(get("/goal/{id}", 1L))
                .andExpect(status().isNotFound());

    }

    @Test
    @DisplayName("POST: Create Goal: Success")
    public void createGoalControllerTest01() throws Exception {
        BDDMockito.doReturn(mockGoal1).when(goalService).create(any());

        mvc.perform(MockMvcRequestBuilders.post("/goal")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(inputGoal)))

                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Is.is(mockGoal1.getTitle())));
    }

    @Test
    @DisplayName("PUT /Goals/1 - Success")
    public void putWidgetTestNotSuccess() throws Exception{
        List<Goal> goals = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/DD/YYY");
        Date targetDate = formatter.parse("02/22/2029");
        goals.add( new Goal("Going to Brazil", 3000.00, 520.00, targetDate, "notes", goalType.VACATION_GOAL));
        goals.add( new Goal("Going to San Juan", 3000.00, 520.00, targetDate, "notes", goalType.VACATION_GOAL));
        Goal expectedGoalUpdate = (new Goal("Going to Brazil", 3000.00, 520.00, targetDate, "notes", goalType.VACATION_GOAL));
        expectedGoalUpdate.setId(1L);
        BDDMockito.doReturn(expectedGoalUpdate).when(goalService).update(new Goal("Going to Brazil", 3000.00, 520.00, targetDate, "notes", goalType.VACATION_GOAL));
        mvc.perform(put("/goal/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(inputGoal)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1L)))
                .andExpect(jsonPath("$.title", is("Going to Brazil")));
    }


    @Test
    @DisplayName("DELETE /Goal/1 - Success")
    public void deleteUserTestNotSuccess() throws Exception{
        BDDMockito.doReturn(true).when(goalService).delete(any());
        mvc.perform(delete("/goal/{id}", 1))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("DELETE /Goal/1 - Not Found")
    public void deleteUserTestNotFound() throws Exception{
        BDDMockito.doThrow(new GoalNotFoundException("Not Found")).when(goalService).delete(any());
        mvc.perform(delete("/goal/{id}", 1L))
                .andExpect(status().isNotFound());
    }





    @Test
    @DisplayName("DELETE /Goal by Title/ - Success")
    public void deleteGoalTestNotSuccess() throws Exception{
        BDDMockito.doReturn(true).when(goalService).deleteGoalByTitle("Going to Hawaii");
        mvc.perform(delete("/goal/{title}", "Going to Hawaii"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("DELETE /Goal by Title/ - Not Found")
    public void deletePersonTestNotFound() throws Exception{
        BDDMockito.doThrow(new GoalNotFoundException("Not Found")).when(goalService).deleteGoalByTitle("Going to Hawaii");
        mvc.perform((delete("/goal/{title}", "Going to Hawaii")))
                .andExpect(status().isNotFound());
    }


}
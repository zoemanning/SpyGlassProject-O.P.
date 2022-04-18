package com.spyglass.optionalintl.user;


import com.spyglass.optionalintl.domain.goal.model.Goal;
import com.spyglass.optionalintl.domain.goal.model.goalType;
import com.spyglass.optionalintl.domain.user.exception.UserNotFoundException;
import com.spyglass.optionalintl.domain.user.model.User;
import com.spyglass.optionalintl.domain.user.services.UserService;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.spyglass.optionalintl.BaseControllerTest.asJsonString;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    private User inputUser;
    private User mockUserResponse01;
    private User mockUserResponse02;

    @BeforeEach
    public void setUp() throws ParseException {

        SimpleDateFormat dateOfBirth01 = new SimpleDateFormat("MM/DD/YYY");
        SimpleDateFormat dateOfBirth02 = new SimpleDateFormat("MM/DD/YYY");

        List<Goal> goals = new ArrayList<>();
        goals.add(new Goal("Going to Hawaii", 3000.00, 520.00, dateOfBirth01.parse("04/18/1999"), "notes",  goalType.VACATION_GOAL));
        goals.add(new Goal("Down payment for house",25000.00,4375.60,dateOfBirth02.parse("07/04/2022"),"notes",goalType.PERSONAL_GOAL));

        inputUser = new User("Zoe", "Manning", dateOfBirth01, "zoe@gmail.com");

        mockUserResponse01 = new User("Zoe", "Manning", dateOfBirth01, "zoe@gmail.com");
        mockUserResponse01.setId(1L);

        mockUserResponse02 = new User("Irlanda", "Manning", dateOfBirth02, "zoe@gmail.com");
        mockUserResponse02.setId(2L);

    }

    @Test
    @DisplayName("Create User")
    public void createUserResponse() throws Exception {
        BDDMockito.doReturn(mockUserResponse01).when(userService).create(any());

        mockMvc.perform(MockMvcRequestBuilders.post("/widgets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(inputUser)))

                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Is.is("Zoe")));
    }

    @Test
    @DisplayName("GET / users/1 - Found")
    public void getUserByIdTestSuccess() throws Exception {
        BDDMockito.doReturn(mockUserResponse01).when(userService).findById(1L);

        mockMvc.perform(get("/widgets/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("Zoe")));
    }

    @Test
    @DisplayName("DELETE /user/1 - Success")
    public void deleteUserTestNotSuccess() throws Exception{
        BDDMockito.doReturn(true).when(userService).delete(any());
        mockMvc.perform(delete("/widgets/{id}", 1))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("DELETE /user/1 - Not Found")
    public void deleteUserTestNotFound() throws Exception{
        BDDMockito.doThrow(new UserNotFoundException("Not Found")).when(userService).delete(any());
        mockMvc.perform(delete("/user/{id}", 1))
                .andExpect(status().isNotFound());
    }

}

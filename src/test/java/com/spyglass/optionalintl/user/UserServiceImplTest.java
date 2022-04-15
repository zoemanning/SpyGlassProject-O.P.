package com.spyglass.optionalintl.user;

import com.spyglass.optionalintl.domain.user.exception.UserNotFoundException;
import com.spyglass.optionalintl.domain.user.model.User;
import com.spyglass.optionalintl.domain.user.repo.UserRepo;
import com.spyglass.optionalintl.domain.user.services.UserService;
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
public class UserServiceImplTest {

    @MockBean
    private UserRepo mockUserRepo;

    @Autowired
    private UserService userService;

    private User inputUser;
    private User outputUser;

    @BeforeEach
    public void setUp() throws ParseException {
        SimpleDateFormat dateOfBirth01 = new SimpleDateFormat("MM/DD/YYY");
        dateOfBirth01.parse("04/18/1995");
        inputUser = new User("Zoe", "Manning", dateOfBirth01, "zoe@gmail.com");
        outputUser = new User("Zoe", "Manning", dateOfBirth01, "zoe@gmail.com");
        outputUser.setId(1L);
    }

    @Test
    public void createUserService() {
        BDDMockito.doReturn(outputUser).when(mockUserRepo).save(ArgumentMatchers.any());
        User returnedUser = userService.create(outputUser);

        Assertions.assertNotNull(userService);
        Assertions.assertEquals(returnedUser.getId(), 1L);
    }

    @Test
    @DisplayName("Find User by Id - Success")
    public void findUserByIdSuccess() throws UserNotFoundException {
        BDDMockito.doReturn(Optional.of(outputUser)).when(mockUserRepo).findById(1L);
        User foundUser = userService.findById(1L);
        Assertions.assertEquals(outputUser.toString(), foundUser.toString());
    }

    @Test
    @DisplayName("Find User by Id - Fail")
    public void findUserByIdFail(){
        BDDMockito.doReturn(Optional.of(outputUser)).when(mockUserRepo).findById(1L);
        Assertions.assertThrows(UserNotFoundException.class, () ->{
            userService.findById(1L);
        });
    }

    @Test
    @DisplayName("Delete User - Success")
    public void deleteUserSuccess(){
        BDDMockito.doReturn(Optional.empty()).when(mockUserRepo).findById(1L);
        Assertions.assertThrows(UserNotFoundException.class, ()-> {
            BDDMockito.doReturn(userService.findById(1L));
        });
    }
}

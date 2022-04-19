package com.spyglass.optionalintl.account;


import com.spyglass.optionalintl.domain.account.model.Account;
import com.spyglass.optionalintl.domain.account.services.AccountService;
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


import static org.hamcrest.core.Is.is;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class AccountControllerTest {

    @MockBean
    private AccountService accountService;

    @Autowired
    private MockMvc mockMvc;

    private Account inputAccount;

    private Account mockResponseAccount1;

    private Account mockResponseAccount2;

    private List<Account> accounts;



    @BeforeEach
    public void setup(){
        accounts = new ArrayList<>();
        accounts.add(new Account("Zimba"));
        accounts.add(new Account("King"));
        inputAccount = new Account("Test Account", accounts);

        mockResponseAccount1 = new Account("Test Account", accounts);
        mockResponseAccount1.setId(223L);

        mockResponseAccount2 = new Account("Test Account", accounts);
        mockResponseAccount2.setId(332L);

    }

    @Test
    @DisplayName("Get / accounts - found")
    public void getAccountByIdTestSuccess() throws Exception{
        BDDMockito.doReturn(mockResponseAccount1).when(accountService).findById(223L);

        mockMvc.perform(get("/account/{id}", 223L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(223)))
                .andExpect(jsonPath("$.name", is("Test Account")));
    }

}

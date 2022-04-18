package com.spyglass.optionalintl.account;


import com.spyglass.optionalintl.domain.account.exception.AccountNotFoundException;
import com.spyglass.optionalintl.domain.account.model.Account;
import com.spyglass.optionalintl.domain.account.repo.AccountRepo;
import com.spyglass.optionalintl.domain.account.services.AccountService;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AccountSerivceImplTest {

    @MockBean
    private AccountRepo accountRepo;

    @Autowired
    private AccountService accountService;

    private Account zimba;
    private Account king;

    private final Long zimbaAccountNumber = 824L;
    private final Long kingAccountNumber = 2345L;
    private final String zimbaEmail = "zimba@gmail.com";
    private final String kingEmail = "king@gmail.com";
    private final List<Account> accounts = new ArrayList<>();

    @BeforeEach
    public void setup(){
        zimba = new Account();
        zimba.setId(223L);
        zimba.setName("Zimba");
        zimba.setDescription("Saving for Vacation");
        zimba.setAccountNumber(824L);
        zimba.setEMail("zimba@gmail.com");

        king = new Account();
        king.setId(332L);
        king.setName("King");
        king.setDescription("Saving for a new car");
        king.setAccountNumber(2345L);
        king.setEMail("king@gmail.com");

        accounts.add(zimba);
        accounts.add(king);


    }


    @Test
    @DisplayName("Find All Accounts")
    public void findAllAccount(){
        BDDMockito.when(accountRepo.findAll()).thenReturn(accounts);
        Iterable<Account> foundAccounts = accountService.findAllAccount();
        assertNotNull(foundAccounts);
        assertEquals(accounts, foundAccounts);

    }

    @Test
    @DisplayName("Update Name")
    public void updateAccountInfo() throws AccountNotFoundException, ParseException {
        SimpleDateFormat updatedDate = new SimpleDateFormat("MM/DD/YYY");
        Account expectedAccountUpdate = new Account( accounts, updatedDate.parse("07/15/2022"));
        expectedAccountUpdate.setId(223L);
        expectedAccountUpdate.setName("Amari Collins");
        BDDMockito.doReturn(Optional.of(accounts)).when(accountRepo).findById(223L);
        BDDMockito.doReturn(expectedAccountUpdate).when(accountRepo).save(ArgumentMatchers.any());
        Account actualAccount = accountService.updateAccountInfo(expectedAccountUpdate);
        Assertions.assertEquals(expectedAccountUpdate.toString(), actualAccount.toString());
    }



    @Test
    @DisplayName("Update Email - Success")
    public void updateEmailTest01() throws AccountNotFoundException, ParseException {
        SimpleDateFormat updatedDate = new SimpleDateFormat("MM/DD/YYY");
        Account expectedAccountUpdate = new Account( accounts, updatedDate.parse("07/04/2022"));
        expectedAccountUpdate.setId(223L);
        expectedAccountUpdate.setEMail("zimbadaking@gmail.com");
        BDDMockito.doReturn(Optional.of(accounts)).when(accountRepo).findById(223L);
        BDDMockito.doReturn(expectedAccountUpdate).when(accountRepo).save(ArgumentMatchers.any());
        Account actualAccount = accountService.updateAccountInfo(expectedAccountUpdate);
        Assertions.assertEquals(expectedAccountUpdate.toString(), actualAccount.toString());
    }

    @Test
    @DisplayName("Delete Account")
    public void deleteAccount() {
        BDDMockito.doReturn(Optional.empty()).when(accountRepo).findById(24L);
        Assertions.assertThrows(AccountNotFoundException.class, () -> {
            accountService.deleteAccount(24L);
        });
    }
}

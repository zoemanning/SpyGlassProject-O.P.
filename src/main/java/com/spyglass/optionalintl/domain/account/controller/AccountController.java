package com.spyglass.optionalintl.domain.account.controller;


import com.spyglass.optionalintl.domain.account.exception.AccountNotFoundException;
import com.spyglass.optionalintl.domain.account.model.Account;
import com.spyglass.optionalintl.domain.account.services.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@Slf4j
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService){this.accountService = accountService;}

    @GetMapping("/all")
    public ResponseEntity<Iterable<Account>> getAll() throws AccountNotFoundException{
        Iterable<Account> accounts = accountService.findAllAccount();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Account> delete(Account account) throws AccountNotFoundException {
        accountService.deleteAccount(account.getId());
        return new ResponseEntity<>(account, HttpStatus.GONE);
    }

}

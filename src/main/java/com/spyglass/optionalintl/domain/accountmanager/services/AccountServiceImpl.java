package com.spyglass.optionalintl.domain.accountmanager.services;


import com.spyglass.optionalintl.domain.accountmanager.exception.AccountNotFoundException;
import com.spyglass.optionalintl.domain.accountmanager.model.Account;
import com.spyglass.optionalintl.domain.accountmanager.repo.AccountRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    private RestTemplate restTemplate;
    private AccountRepo accountRepo;

    @Autowired
    public AccountServiceImpl(AccountRepo accountRepo){
        this.restTemplate = new RestTemplate();
        this.accountRepo = accountRepo;
    }


    @Override
    public Account createNewAccount(Account account) {
        return null;
    }

    @Override
    public Account save(Account account) {
        return accountRepo.save(account);
    }

    @Override
    public Account requestAccount(Long id) throws AccountNotFoundException {
        Optional<Account> optional = accountRepo.findById(id);
        if (optional.isEmpty()){
            throw new AccountNotFoundException(" Account not found ");
        }
        return optional.get();
    }

    @Override
    public Iterable<Account> findAllAccount() {
        return accountRepo.findAll();
    }

    @Override
    public Account updateAccountInfo(Account account) throws AccountNotFoundException {
        Long id = account.getId();
        Optional<Account> accountOptional = accountRepo.findById(id);
        if (accountOptional.isEmpty())
            throw new AccountNotFoundException(" Account not found " + id);
        return accountRepo.save(account);
    }

    @Override
    public void deleteAccount(Long id) throws AccountNotFoundException {
        Optional<Account> accountOptional = accountRepo.findById(id);
        if (accountOptional.isEmpty())
            throw new AccountNotFoundException(" Account not found " + id);
        Account accountToRemove = accountOptional.get();
        accountRepo.delete(accountToRemove);

    }
}











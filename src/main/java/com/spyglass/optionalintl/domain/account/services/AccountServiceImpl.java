package com.spyglass.optionalintl.domain.account.services;


import com.spyglass.optionalintl.domain.account.exception.AccountNotFoundException;
import com.spyglass.optionalintl.domain.account.model.Account;
import com.spyglass.optionalintl.domain.account.repo.AccountRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    private AccountRepo accountRepo;

    @Autowired
    public AccountServiceImpl(AccountRepo accountRepo){
        this.accountRepo = accountRepo;
    }


    @Override
    public Account findById(Long id) throws AccountNotFoundException {
        Optional<Account> accountOptional = accountRepo.findById(id);
        if (accountOptional.isEmpty())
            throw new AccountNotFoundException("Account not found");
        return accountOptional.get();
    }

    @Override
    public List<Account> findAllAccount() {
        return (List)accountRepo.findAll();
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











package com.spyglass.optionalintl.domain.account.services;

import com.spyglass.optionalintl.domain.account.exception.AccountNotFoundException;
import com.spyglass.optionalintl.domain.account.model.Account;

import java.util.List;

public interface AccountService {

    Account findById(Long id) throws AccountNotFoundException;

    List<Account> findAllAccount();

    Account updateAccountInfo(Account account) throws AccountNotFoundException;

    void deleteAccount(Long id) throws AccountNotFoundException;
}

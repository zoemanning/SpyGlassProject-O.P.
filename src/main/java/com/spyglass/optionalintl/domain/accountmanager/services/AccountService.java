package com.spyglass.optionalintl.domain.accountmanager.services;

import com.spyglass.optionalintl.domain.accountmanager.exception.AccountNotFoundException;
import com.spyglass.optionalintl.domain.accountmanager.model.Account;

public interface AccountService {

    Iterable<Account> findAllAccount();

    Account updateAccountInfo(Account account) throws AccountNotFoundException;

    void deleteAccount(Long id) throws AccountNotFoundException;
}

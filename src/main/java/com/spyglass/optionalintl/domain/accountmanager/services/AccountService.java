package com.spyglass.optionalintl.domain.accountmanager.services;

import com.spyglass.optionalintl.domain.accountmanager.exception.AccountNotFoundException;
import com.spyglass.optionalintl.domain.accountmanager.model.Account;

public interface AccountService {

    Account createNewAccount(Account account);

    Account save(Account account);

    Account requestAccount(Long id) throws AccountNotFoundException;

    Iterable<Account> findAllAccount();

    Account updateAccountInfo(Account account) throws AccountNotFoundException;

    void deleteAccount(Long id) throws AccountNotFoundException;
}

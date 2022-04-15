package com.spyglass.optionalintl.domain.account.services;

import com.spyglass.optionalintl.domain.account.exception.AccountNotFoundException;
import com.spyglass.optionalintl.domain.account.model.Account;

public interface AccountService {

    Iterable<Account> findAllAccount();

    Account updateAccountInfo(Account account) throws AccountNotFoundException;

    void deleteAccount(Long id) throws AccountNotFoundException;
}

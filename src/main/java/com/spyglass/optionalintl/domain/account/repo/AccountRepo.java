package com.spyglass.optionalintl.domain.account.repo;

import com.spyglass.optionalintl.domain.account.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepo extends CrudRepository<Account, Long> {
}

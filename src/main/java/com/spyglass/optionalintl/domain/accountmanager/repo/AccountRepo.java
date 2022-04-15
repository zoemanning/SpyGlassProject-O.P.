package com.spyglass.optionalintl.domain.accountmanager.repo;

import com.spyglass.optionalintl.domain.accountmanager.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepo extends CrudRepository<Account, Long> {
}

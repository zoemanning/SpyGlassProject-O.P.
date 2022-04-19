package com.spyglass.optionalintl.domain.account.repo;

import com.spyglass.optionalintl.domain.account.model.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepo extends CrudRepository<Account, Long> {

}

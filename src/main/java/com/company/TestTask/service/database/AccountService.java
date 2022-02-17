package com.company.TestTask.service.database;

import com.company.TestTask.entity.Account;
import com.company.TestTask.service.base.CrudService;

public interface AccountService extends CrudService<Account> {
    Account getAccountByAccountCode(String accountCode);
}

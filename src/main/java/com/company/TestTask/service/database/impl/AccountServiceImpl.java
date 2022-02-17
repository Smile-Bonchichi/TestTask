package com.company.TestTask.service.database.impl;

import com.company.TestTask.entity.Account;
import com.company.TestTask.repository.AccountRepository;
import com.company.TestTask.service.base.impl.CrudServiceImpl;
import com.company.TestTask.service.database.AccountService;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountServiceImpl extends CrudServiceImpl<Account> implements AccountService {
    @Autowired
    AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        super(accountRepository);
        this.accountRepository = accountRepository;
    }

    @Override
    public Account getAccountByAccountCode(String accountCode) {
        return accountRepository.findAccountByAccountCode(accountCode);
    }
}

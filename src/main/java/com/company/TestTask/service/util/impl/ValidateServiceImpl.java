package com.company.TestTask.service.util.impl;

import com.company.TestTask.dto.request.Request;
import com.company.TestTask.dto.request.SupplierDto;
import com.company.TestTask.entity.Account;
import com.company.TestTask.service.database.AccountService;
import com.company.TestTask.service.database.SupplierService;
import com.company.TestTask.service.util.ValidateService;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ValidateServiceImpl implements ValidateService {
    @Autowired
    AccountService accountService;

    @Autowired
    SupplierService supplierService;

    @Override
    public boolean checkParams(Request request) {
        return request.getSupplier() == null ||
                request.getSupplier().getName() == null ||

                request.getPayParams() == null ||
                request.getPayParams().getDocumentNumber() == null ||
                request.getPayParams().getAmount() == null ||
                request.getPayParams().getCurrency() == null ||
                request.getPayParams().getAccountCode() == null ||
                request.getPayParams().getInn() == null;
    }

    @Override
    public boolean checkAccountClient(String account) {
        return accountService.getAccountByAccountCode(account) == null;
    }

    @Override
    public boolean checkSupplier(SupplierDto supplierDto) {
        return supplierService.getBySupplierByName(supplierDto.getName()) == null;
    }

    @Override
    public boolean checkHavingAmountOnSupplier(SupplierDto supplierDto) {
        Account account = accountService.getAccountByAccountCode(supplierDto.getName());
        if (account == null)
            return false;
        return account.getAmount().compareTo(BigDecimal.valueOf(0L)) >= 0;
    }
}

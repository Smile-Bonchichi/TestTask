package com.company.TestTask.service.util;

import com.company.TestTask.dto.request.Request;
import com.company.TestTask.dto.request.SupplierDto;

import java.math.BigDecimal;

public interface ValidateService {
    boolean checkParams(Request request);

    boolean checkAccountClient(String account);

    boolean checkSupplier(SupplierDto supplierDto);

    boolean checkHavingAmountOnSupplier(String supplierName, BigDecimal amount);
}

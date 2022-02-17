package com.company.TestTask.service.impl;

import com.company.TestTask.dto.request.Request;
import com.company.TestTask.dto.response.Response;
import com.company.TestTask.factory.ResponseFactory;
import com.company.TestTask.service.CheckAccountService;
import com.company.TestTask.service.database.SupplierService;
import com.company.TestTask.service.util.ValidateService;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CheckAccountServiceImpl implements CheckAccountService {
    @Autowired
    SupplierService supplierService;

    @Autowired
    ValidateService validateService;

    @Autowired
    ResponseFactory responseFactory;

    @Override
    public Response checkAccount(Request request) {
        if (validateService.checkParams(request))
            return responseFactory.getInvalidDetailsResponse();

        if (validateService.checkSupplier(request.getSupplier()))
            return responseFactory.getSupplierErrorResponse();

        if (validateService.checkAccountClient(
                supplierService
                        .getBySupplierByName(
                                request
                                        .getSupplier()
                                        .getName())
                        .getSupplierCode()))
            return responseFactory.getInvalidDetailsResponse();

        if (validateService.checkHavingAmountOnSupplier(request.getSupplier()))
            return responseFactory.getNotHaveAmountOnAccountResponse();


        return null;
    }
}

package com.company.TestTask.service;

import com.company.TestTask.dto.request.AuthSupplierDto;
import com.company.TestTask.dto.response.Response;

public interface SignInService {
    Response getTokenSupplier(AuthSupplierDto authSupplierDto);
}

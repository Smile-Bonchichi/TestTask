package com.company.TestTask.service;

import com.company.TestTask.dto.request.Request;
import com.company.TestTask.dto.response.Response;

public interface CheckAccountService {
    Response checkAccount(Request request);
}

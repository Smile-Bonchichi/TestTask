package com.company.TestTask.service;

import com.company.TestTask.dto.request.Request;
import com.company.TestTask.dto.response.Response;

public interface PaymentService {
    Response makePayment(Request request);
}

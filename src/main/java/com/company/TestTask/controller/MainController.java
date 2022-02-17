package com.company.TestTask.controller;

import com.company.TestTask.dto.request.AuthSupplierDto;
import com.company.TestTask.dto.request.Request;
import com.company.TestTask.dto.response.Response;
import com.company.TestTask.service.CheckAccountService;
import com.company.TestTask.service.PaymentService;
import com.company.TestTask.service.SignInService;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MainController {
    @Autowired
    CheckAccountService checkAccountService;

    @Autowired
    PaymentService paymentService;

    @Autowired
    SignInService signInService;

    @PostMapping("check")
    public Response checkAccount(@RequestBody Request request) {
        return checkAccountService.checkAccount(request);
    }

    @PostMapping("pay")
    public Response makePayment(@RequestBody Request request) {
        return paymentService.makePayment(request);
    }

    @PostMapping("get-token")
    public Response getToken(@RequestBody AuthSupplierDto authSupplierDto) {
        return signInService.getTokenSupplier(authSupplierDto);
    }
}

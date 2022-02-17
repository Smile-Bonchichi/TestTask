package com.company.TestTask.controller;

import com.company.TestTask.dto.request.Request;
import com.company.TestTask.dto.response.Response;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/", produces = "application/json; charset=utf-8")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MainController {
    @PostMapping("check")
    public Response checkAccount(@RequestBody Request request) {
        return null;
    }

    @PostMapping("pay")
    public Response makePayment(@RequestBody Request request) {
        return null;
    }
}

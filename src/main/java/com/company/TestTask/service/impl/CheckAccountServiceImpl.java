package com.company.TestTask.service.impl;

import com.company.TestTask.dto.request.Request;
import com.company.TestTask.dto.response.Response;
import com.company.TestTask.entity.PaymentDocument;
import com.company.TestTask.factory.ResponseFactory;
import com.company.TestTask.service.CheckAccountService;
import com.company.TestTask.service.database.PaymentDocumentService;
import com.company.TestTask.service.database.SupplierService;
import com.company.TestTask.service.util.ValidateService;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CheckAccountServiceImpl implements CheckAccountService {
    @Autowired
    ValidateService validateService;

    @Autowired
    ResponseFactory responseFactory;

    @Autowired
    PaymentDocumentService paymentDocumentService;

    @Autowired
    SupplierService supplierService;

    @Override
    public Response checkAccount(Request request) {
        if (validateService.checkParams(request))
            return responseFactory.getInvalidDetailsResponse();

        if (validateService.checkSupplier(request.getSupplier()))
            return responseFactory.getSupplierErrorResponse();

        if (validateService.checkAccountClient(request.getPayParams().getAccountCode()))
            return responseFactory.getInvalidDetailsResponse();

        PaymentDocument paymentDocument = paymentDocumentService.getResponseBodyByDocumentNumber(request.getPayParams().getDocumentNumber(), "CHECK");

        if (paymentDocument != null && paymentDocument.getType().equals("CHECK"))
            return Response.builder()
                    .idPayment(paymentDocument.getId().toString())
                    .code(1L)
                    .description("Уже была такая проверка")
                    .build();

        paymentDocument = paymentDocumentService.saveOrUpdate(
                PaymentDocument.builder()
                        .documentNumber(request.getPayParams().getDocumentNumber())
                        .type("CHECK")
                        .amount(request.getPayParams().getAmount())
                        .currency(request.getPayParams().getCurrency())
                        .recipientAccount(request.getPayParams().getAccountCode())
                        .payerAccount(supplierService.getBySupplierByName(request.getSupplier().getSupplierName()).getSupplierCode())
                        .inputDate(LocalDateTime.now())
                        .requestBody(request.toString())
                        .build()
        );

        return Response.builder()
                .idPayment(paymentDocument.getId().toString())
                .code(0L)
                .description("Успешно")
                .build();
    }
}

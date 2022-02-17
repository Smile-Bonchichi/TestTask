package com.company.TestTask.service.impl;

import com.company.TestTask.dto.request.Request;
import com.company.TestTask.dto.response.Response;
import com.company.TestTask.entity.Account;
import com.company.TestTask.entity.PaymentDocument;
import com.company.TestTask.factory.ResponseFactory;
import com.company.TestTask.service.PaymentService;
import com.company.TestTask.service.database.AccountService;
import com.company.TestTask.service.database.PaymentDocumentService;
import com.company.TestTask.service.database.SupplierService;
import com.company.TestTask.service.util.ValidateService;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    ValidateService validateService;

    @Autowired
    ResponseFactory responseFactory;

    @Autowired
    AccountService accountService;

    @Autowired
    PaymentDocumentService paymentDocumentService;

    @Autowired
    SupplierService supplierService;

    @Override
    public Response makePayment(Request request) {
        if (validateService.checkParams(request))
            return responseFactory.getInvalidDetailsResponse();

        if (validateService.checkSupplier(request.getSupplier()))
            return responseFactory.getSupplierErrorResponse();

        if (validateService.checkAccountClient(request.getPayParams().getAccountCode()))
            return responseFactory.getInvalidDetailsResponse();

        if (validateService.checkHavingAmountOnSupplier(
                request.getSupplier().getSupplierName(),
                request.getPayParams().getAmount())
        ) return responseFactory.getNotHaveAmountOnAccountResponse();

        PaymentDocument paymentDocument = paymentDocumentService.getResponseBodyByDocumentNumber(request.getPayParams().getDocumentNumber(), "PAY");

        if (paymentDocument != null && paymentDocument.getType().equals("PAY"))
            return Response.builder()
                    .idPayment(paymentDocument.getId().toString())
                    .code(1L)
                    .description("Уже было такое пополнение")
                    .build();

        return paying(request);
    }

    private Response paying(Request request) {
        Account recipientAccount = accountService.getAccountByAccountCode(
                request.getPayParams().getAccountCode()
        );

        Account payerAccount = accountService.getAccountByAccountCode(
                supplierService.getBySupplierByName(
                        request.getSupplier().getSupplierName()
                ).getSupplierCode()
        );

        PaymentDocument paymentDocument = paymentDocumentService.saveOrUpdate(
                PaymentDocument.builder()
                        .documentNumber(request.getPayParams().getDocumentNumber())
                        .type("PAY")
                        .amount(request.getPayParams().getAmount())
                        .currency(request.getPayParams().getCurrency())
                        .recipientAccount(recipientAccount.getAccountCode())
                        .payerAccount(payerAccount.getAccountCode())
                        .status(0L)
                        .inputDate(LocalDateTime.now())
                        .requestBody(request.toString())
                        .build()
        );

        BigDecimal recipientAmount = recipientAccount.getAmount();
        BigDecimal payerAmount = payerAccount.getAmount();

        recipientAccount.setAmount(recipientAmount.add(request.getPayParams().getAmount()));
        recipientAccount = accountService.saveOrUpdate(recipientAccount);

        payerAccount.setAmount(payerAmount.subtract(request.getPayParams().getAmount()));
        payerAccount = accountService.saveOrUpdate(payerAccount);

        if (recipientAccount.getAmount()
                .subtract(request.getPayParams()
                        .getAmount()
                ).compareTo(recipientAmount) == 0
                && payerAccount.getAmount()
                .add(request.getPayParams()
                        .getAmount()
                ).compareTo(payerAmount) == 0)
            return Response.builder()
                    .idPayment(paymentDocument.getId().toString())
                    .code(0L)
                    .description("Успешно")
                    .build();

        return responseFactory.getFailPaymentResponse();
    }
}

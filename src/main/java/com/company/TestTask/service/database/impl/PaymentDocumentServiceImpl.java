package com.company.TestTask.service.database.impl;

import com.company.TestTask.entity.PaymentDocument;
import com.company.TestTask.repository.PaymentDocumentRepository;
import com.company.TestTask.service.base.impl.CrudServiceImpl;
import com.company.TestTask.service.database.PaymentDocumentService;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentDocumentServiceImpl extends CrudServiceImpl<PaymentDocument> implements PaymentDocumentService {
    @Autowired
    PaymentDocumentRepository paymentDocumentRepository;

    public PaymentDocumentServiceImpl(PaymentDocumentRepository paymentDocumentRepository) {
        super(paymentDocumentRepository);
        this.paymentDocumentRepository = paymentDocumentRepository;
    }
}

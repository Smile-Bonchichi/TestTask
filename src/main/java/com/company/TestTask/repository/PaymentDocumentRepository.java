package com.company.TestTask.repository;

import com.company.TestTask.entity.PaymentDocument;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDocumentRepository extends JpaRepository<PaymentDocument, Long> {
}

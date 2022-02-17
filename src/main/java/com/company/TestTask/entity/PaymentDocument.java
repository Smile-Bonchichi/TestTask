package com.company.TestTask.entity;

import com.company.TestTask.entity.base.BaseEntity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment_documents")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentDocument extends BaseEntity {
    @Column(name = "document_number", unique = true, nullable = false)
    String documentNumber;

    @Column(name = "type", nullable = false)
    String type;

    @Column(name = "amount")
    BigDecimal amount;

    @Column(name = "currency", nullable = false)
    String currency;

    @Column(name = "account_code", nullable = false) //счет куда зачисляют ДС
    String accountCode;

    @Column(name = "client_code", nullable = false) //счет откуда приходят ДС
    String clientCode;

    @Column(name = "inn", nullable = false)
    String inn;

    @Column(name = "status")
    Long status;

    @Column(name = "input_date", nullable = false)
    LocalDateTime inputDate;
}

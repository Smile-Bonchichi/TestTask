package com.company.TestTask.entity;

import com.company.TestTask.entity.base.BaseEntity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "account")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account extends BaseEntity {
    @Column(name = "account_code", unique = true, nullable = false)
    String accountCode;

    @Column(name = "amount", nullable = false)
    BigDecimal amount;

    @Column(name = "inn", unique = true, nullable = false)
    String inn;

    @Column(name = "status_account", unique = true, nullable = false)
    Boolean statusAcoount;
}

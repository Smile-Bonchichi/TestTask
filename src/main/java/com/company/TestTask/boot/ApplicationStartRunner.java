package com.company.TestTask.boot;

import com.company.TestTask.entity.Account;
import com.company.TestTask.entity.Role;
import com.company.TestTask.entity.Supplier;
import com.company.TestTask.entity.SupplierRole;
import com.company.TestTask.service.database.AccountService;
import com.company.TestTask.service.database.RoleService;
import com.company.TestTask.service.database.SupplierRoleService;
import com.company.TestTask.service.database.SupplierService;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplicationStartRunner implements CommandLineRunner {
    @Autowired
    RoleService roleService;

    @Autowired
    SupplierRoleService supplierRoleService;

    @Autowired
    AccountService accountService;

    @Autowired
    SupplierService supplierService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        Role role = roleService.saveOrUpdate(Role.builder()
                .nameRole("ROLE_USER")
                .build());

        roleService.saveOrUpdate(Role.builder()
                .nameRole("ROLE_ADMIN")
                .build());

        Supplier supplier = supplierService.saveOrUpdate(Supplier.builder()
                .supplierName("MPC")
                .password(passwordEncoder.encode("MPC"))
                .supplierCode("1234567890098765")
                .isActive(1L)
                .build());

        supplierRoleService.saveOrUpdate(SupplierRole.builder()
                .supplier(supplier)
                .role(role)
                .build());

        supplier = supplierService.saveOrUpdate(Supplier.builder()
                .supplierName("MGC")
                .password(passwordEncoder.encode("MGC"))
                .supplierCode("9876543210123456")
                .isActive(1L)
                .build());

        supplierRoleService.saveOrUpdate(SupplierRole.builder()
                .supplier(supplier)
                .role(role)
                .build());

        supplier = supplierService.saveOrUpdate(Supplier.builder()
                .supplierName("ODN")
                .supplierCode("6653242134314312")
                .password(passwordEncoder.encode("ODN"))
                .isActive(1L)
                .build());

        supplierRoleService.saveOrUpdate(SupplierRole.builder()
                .supplier(supplier)
                .role(role)
                .build());

        accountService.saveOrUpdate(Account.builder()
                .accountCode("1234567890098765")
                .amount(BigDecimal.valueOf(1000000))
                .fullName("Транзитный счет для МПС")
                .inn("12321312312541")
                .currency("KGS")
                .statusAccount("0")
                .build());

        accountService.saveOrUpdate(Account.builder()
                .accountCode("9876543210123456")
                .amount(BigDecimal.valueOf(1000000))
                .fullName("Транзитный счет для МегаПей")
                .inn("12312354322131")
                .currency("KGS")
                .statusAccount("0")
                .build());

        accountService.saveOrUpdate(Account.builder()
                .accountCode("6653242134314312")
                .amount(BigDecimal.valueOf(1000000))
                .fullName("Транзитный счет для О! Деньги")
                .inn("56546345634563")
                .currency("KGS")
                .statusAccount("0")
                .build());

        accountService.saveOrUpdate(Account.builder()
                .accountCode("2131231411231231")
                .amount(BigDecimal.ZERO)
                .fullName("Улан у. Нурдин")
                .inn("89548976054612")
                .currency("KGS")
                .statusAccount("0")
                .build());
    }
}

package com.company.TestTask.service.database.impl;

import com.company.TestTask.entity.SupplierRole;
import com.company.TestTask.repository.SupplierRoleRepository;
import com.company.TestTask.service.base.impl.CrudServiceImpl;
import com.company.TestTask.service.database.SupplierRoleService;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SupplierRoleServiceImpl extends CrudServiceImpl<SupplierRole> implements SupplierRoleService {
    @Autowired
    SupplierRoleRepository supplierRoleRepository;

    public SupplierRoleServiceImpl(SupplierRoleRepository supplierRoleRepository) {
        super(supplierRoleRepository);
        this.supplierRoleRepository = supplierRoleRepository;
    }
}

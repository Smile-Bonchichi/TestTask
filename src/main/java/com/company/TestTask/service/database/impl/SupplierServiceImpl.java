package com.company.TestTask.service.database.impl;

import com.company.TestTask.entity.Supplier;
import com.company.TestTask.repository.SupplierRepository;
import com.company.TestTask.service.base.impl.CrudServiceImpl;
import com.company.TestTask.service.database.SupplierService;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SupplierServiceImpl extends CrudServiceImpl<Supplier> implements SupplierService {
    @Autowired
    SupplierRepository supplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        super(supplierRepository);
        this.supplierRepository = supplierRepository;
    }

    @Override
    public Supplier getBySupplierByName(String supplierName) {
        return supplierRepository.findBySupplierName(supplierName);
    }
}

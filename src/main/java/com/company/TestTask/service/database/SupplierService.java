package com.company.TestTask.service.database;

import com.company.TestTask.entity.Supplier;
import com.company.TestTask.service.base.CrudService;

public interface SupplierService extends CrudService<Supplier> {
    Supplier getBySupplierByName(String supplierName);
}

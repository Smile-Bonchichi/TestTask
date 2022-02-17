package com.company.TestTask.repository;

import com.company.TestTask.entity.SupplierRole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRoleRepository extends JpaRepository<SupplierRole, Long> {
}

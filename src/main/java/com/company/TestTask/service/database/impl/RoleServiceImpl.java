package com.company.TestTask.service.database.impl;

import com.company.TestTask.entity.Role;
import com.company.TestTask.repository.RoleRepository;
import com.company.TestTask.service.base.impl.CrudServiceImpl;
import com.company.TestTask.service.database.RoleService;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleServiceImpl extends CrudServiceImpl<Role> implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        super(roleRepository);
        this.roleRepository = roleRepository;
    }
}

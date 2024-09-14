package com.example.backend_rw.service.impl;

import com.example.backend_rw.entity.Role;
import com.example.backend_rw.repository.RoleRepository;
import com.example.backend_rw.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}

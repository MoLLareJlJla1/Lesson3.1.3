package ru.kata.spring.boot_security.demo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.entity.Role;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService{
   private final RoleDao roleRepository;

    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role findById(long l) {

        return roleRepository.findById(l);
    }
}

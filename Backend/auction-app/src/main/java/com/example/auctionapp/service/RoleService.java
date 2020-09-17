package com.example.auctionapp.service;

import com.example.auctionapp.dto.RoleDto;
import com.example.auctionapp.model.Role;
import com.example.auctionapp.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RoleService implements IBaseService<RoleDto> {

    BaseRepository<Role> repository;

    @Autowired
    public void setRepository(BaseRepository<Role> repositoryToSet) {
        repository = repositoryToSet;
        repository.setResourceClass(Role.class);
    }


    public List<RoleDto> getAll() {

        List<Role> roles = repository.findAll();
        List<RoleDto> roleDtos = new ArrayList<>();

        for (Role role:roles) {
            roleDtos.add(new RoleDto(role.getId(), role.getDateCreated(), role.getLastModifiedDate(), role.getName()));
        }

        return  roleDtos;
    }


    public RoleDto getById(Long id) {
        Role role = repository.findById(id);
        return new RoleDto(role.getId(), role.getDateCreated(), role.getLastModifiedDate(), role.getName());
    }


    public RoleDto add(RoleDto resource) {
        repository.create(new Role(LocalDate.now(), LocalDate.now(), resource.getName()));
        return resource;
    }


    public RoleDto update(RoleDto resource) {
        Role resourceToUpdate = repository.findById(resource.getId());
        resourceToUpdate.setLastModifiedDate(LocalDate.now());
        repository.update(resourceToUpdate);
        return resource;
    }


    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

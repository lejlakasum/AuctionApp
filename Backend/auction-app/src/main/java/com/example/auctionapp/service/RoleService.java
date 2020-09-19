package com.example.auctionapp.service;

import com.example.auctionapp.dto.RoleDto;
import com.example.auctionapp.exception.NotFoundException;
import com.example.auctionapp.model.Role;
import com.example.auctionapp.repository.BaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RoleService implements IBaseService<RoleDto> {

    BaseRepository<Role> repository;

    private Logger logger = LoggerFactory.getLogger(RoleService.class);

    @Autowired
    public RoleService(BaseRepository<Role> repository) {
        this.repository = repository;
        this.repository.setResourceClass(Role.class);
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
        if(role == null) {
            String message = "Role with id " + id + " does not exist";
            logger.error(message);
            throw new NotFoundException(message);
        }
        return new RoleDto(role.getId(), role.getDateCreated(), role.getLastModifiedDate(), role.getName());
    }


    public RoleDto add(RoleDto resource) {
        Role role = repository.create(new Role(resource.getName()));
        logger.info("Role with id " + role.getId() + " created");
        return new RoleDto(role.getId(), role.getDateCreated(), role.getLastModifiedDate(), role.getName());
    }


    public RoleDto update(RoleDto resource) {
        Role resourceToUpdate = repository.findById(resource.getId());
        if(resourceToUpdate == null) {
            String message = "Role with id " + resource.getId() + " does not exist";
            logger.error(message);
            throw new NotFoundException(message);
        }
        resourceToUpdate.setName(resource.getName());

        Role role = repository.update(resourceToUpdate);
        logger.info("Role with id " + role.getId() + " updated");

        return new RoleDto(role.getId(), role.getDateCreated(), role.getLastModifiedDate(), role.getName());
    }


    public void deleteById(Long id) {

        if(repository.findById(id) == null) {
            String message = "Role with id " + id + " does not exist";
            logger.error(message);
            throw new NotFoundException(message);
        }

        repository.deleteById(id);
        logger.info("Role with id " + id + " deleted");
    }
}

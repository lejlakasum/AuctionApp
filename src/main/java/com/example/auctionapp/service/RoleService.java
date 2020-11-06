package com.example.auctionapp.service;

import com.example.auctionapp.Util.RepositoryUtility;
import com.example.auctionapp.dto.RoleDto;
import com.example.auctionapp.model.Role;
import com.example.auctionapp.repository.BaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleService implements IBaseService<RoleDto> {

    private static final Logger logger = LoggerFactory.getLogger(RoleService.class);
    private static final String RESOURCE_NAME = "Role";

    private final BaseRepository<Role> repository;

    @Autowired
    public RoleService(BaseRepository<Role> repository) {
        this.repository = repository;
    }

    public List<RoleDto> getAll() {

        List<Role> roles = repository.findAll();
        List<RoleDto> roleDtos = roles.stream().map(
                role -> {return new RoleDto(
                                    role.getId(),
                                    role.getDateCreated(),
                                    role.getLastModifiedDate(),
                                    role.getName());
                }
        ).collect(Collectors.toList());

        return  roleDtos;
    }


    public RoleDto getById(Long id) {

        Role role = RepositoryUtility.findIfExist(repository, id, RESOURCE_NAME);
        return new RoleDto(role.getId(), role.getDateCreated(), role.getLastModifiedDate(), role.getName());
    }


    public RoleDto add(RoleDto resource) {
        Role role = repository.create(new Role(resource.getName()));
        logger.info("Role with id " + role.getId() + " created");
        return new RoleDto(role.getId(), role.getDateCreated(), role.getLastModifiedDate(), role.getName());
    }


    public RoleDto update(RoleDto resource) {
        Role resourceToUpdate = RepositoryUtility.findIfExist(repository, resource.getId(), RESOURCE_NAME);
        resourceToUpdate.setName(resource.getName());

        Role role = repository.update(resourceToUpdate);
        logger.info("Role with id " + role.getId() + " updated");

        return new RoleDto(role.getId(), role.getDateCreated(), role.getLastModifiedDate(), role.getName());
    }


    public void deleteById(Long id) {

        RepositoryUtility.findIfExist(repository, id, RESOURCE_NAME);

        repository.deleteById(id);
        logger.info("Role with id " + id + " deleted");
    }
}

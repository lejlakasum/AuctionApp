package com.example.auctionapp.service;

import com.example.auctionapp.dto.UserDto;
import com.example.auctionapp.exception.NotFoundException;
import com.example.auctionapp.model.Role;
import com.example.auctionapp.model.User;
import com.example.auctionapp.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService implements IBaseService<UserDto> {

    private BaseRepository<User> userRepository;
    private BaseRepository<Role> roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(BaseRepository<User> repositoryToSet) {
        userRepository = repositoryToSet;
        userRepository.setResourceClass(User.class);
    }

    @Autowired
    public void setRoleRepository(BaseRepository<Role> repositoryToSet) {
        roleRepository = repositoryToSet;
        roleRepository.setResourceClass(Role.class);
    }

    @Autowired
    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserDto> getAll() {

        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();

        for (User user:users) {
            userDtos.add(mapUserToUserDto(user));
        }

        return  userDtos;
    }


    public UserDto getById(Long id) {

        User user = userRepository.findById(id);
        if(user == null) {
            throw new NotFoundException("User with id " + id + " does not exist");
        }
        return mapUserToUserDto(user);
    }


    public UserDto add(UserDto resource) {
        Role role = roleRepository.findById(resource.getRoleId());
        if(role==null) {
            throw new NotFoundException("Role with id " + resource.getRoleId() + " does not exist");
        }

        User user = userRepository.create(new User(resource.getFirstName(),
                resource.getLastName(),
                resource.getEmail(),
                passwordEncoder.encode(resource.getPassword()),
                role));

        return mapUserToUserDto(user);
    }


    public UserDto update(UserDto resource) {
        User resourceToUpdate = userRepository.findById(resource.getId());
        if(resourceToUpdate == null) {
            throw new NotFoundException("User with id " + resource.getId() + " does not exist");
        }

        resourceToUpdate.setFirstName(resource.getFirstName());
        resourceToUpdate.setLastName(resource.getLastName());
        resourceToUpdate.setEmail(resource.getEmail());

        User user = userRepository.update(resourceToUpdate);

        return mapUserToUserDto(user);
    }


    public void deleteById(Long id) {

        if(userRepository.findById(id) == null) {
            throw new NotFoundException("User with id " + id + " does not exist");
        }

        userRepository.deleteById(id);
    }

    private UserDto mapUserToUserDto(User user) {
        return new UserDto(user.getId(),
                user.getDateCreated(),
                user.getLastModifiedDate(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole().getId());
    }
}

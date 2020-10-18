package com.example.auctionapp.Service;

import com.example.auctionapp.model.Role;
import com.example.auctionapp.repository.BaseRepository;
import com.example.auctionapp.service.RoleService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class RoleServiceTest {

    @InjectMocks
    private RoleService roleService;

    @Mock
    private BaseRepository<Role> roleRepository;

    @Before
    public void init() {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getByIdRoleTest() {

        Role role = new Role("roleName");

        Mockito.when(roleRepository.findById(1L))
                .thenReturn(role);
        assertEquals(role.getName(), roleService.getById(1L).getName());

    }

    @Test
    public void getRolesTest() {

        Role role1 = new Role("roleName");
        Role role2 = new Role("roleName");

        Mockito.when(roleRepository.findAll())
                .thenReturn(Stream.of(role1, role2).collect(Collectors.toList()));

        assertEquals(2, roleService.getAll().size());

    }
}

package com.example.auctionapp;

import com.example.auctionapp.dto.RoleDto;
import com.example.auctionapp.model.Category;
import com.example.auctionapp.model.Image;
import com.example.auctionapp.model.LoginRequest;
import com.example.auctionapp.model.Role;
import com.example.auctionapp.repository.BaseRepository;
import com.example.auctionapp.security.CustomUserDetails;
import com.example.auctionapp.security.JwtUtil;
import com.example.auctionapp.security.RepositoryAwareUserDetailsService;
import com.example.auctionapp.service.AuthenticationService;
import static org.junit.Assert.assertEquals;

import com.example.auctionapp.service.RoleService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
class AuctionAppApplicationTests {

    @Value("${secret-key}")
    private String SECRET_KEY;

    @InjectMocks
    private AuthenticationService authenticationService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private RepositoryAwareUserDetailsService userDetailsService;

    @InjectMocks
    private RoleService roleService;

    @Mock
    private BaseRepository<Role> roleRepository;

    @InjectMocks
    private BaseRepository<Category> categoryRepository;

    @Mock
    private EntityManager entityManager;

    @Test
    void contextLoads() {
    }

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void loginTest() throws Exception {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken("username",
                                                                                                    "password");
        CustomUserDetails userDetails = new CustomUserDetails(
                                            "username",
                                            "password",
                                                    Arrays.asList(new SimpleGrantedAuthority("authority")),
                                                    1000L);

        LoginRequest request = new LoginRequest("username", "password");

        Mockito.when(authenticationManager.authenticate(authentication))
                .thenReturn(authentication);

        Mockito.when(userDetailsService.loadUserByUsername("username"))
                .thenReturn(userDetails);

        assertEquals(JwtUtil.generateToken(userDetails, SECRET_KEY), authenticationService.login(request).getToken());

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

    @Test
    public void findCategoryTest() {

        Category category = new Category("categoryName", new Image("#"));

        Mockito.when(entityManager.find(Category.class, 1L))
                .thenReturn(category);

        Category serviceCategory = categoryRepository.findById(1L);
        assertEquals(category.getName(), serviceCategory.getName());
    }

    @Test
    public void createCategoryTest() {

        Category category = new Category("categoryName", new Image("#"));

        Mockito.doNothing().when(entityManager).persist(category);

        assertEquals(category.getName(), categoryRepository.create(category).getName());
    }

}

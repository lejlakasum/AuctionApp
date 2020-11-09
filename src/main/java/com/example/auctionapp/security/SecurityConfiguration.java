package com.example.auctionapp.security;

import com.example.auctionapp.enumeration.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final RepositoryAwareUserDetailsService myUserDetailService;
    private final JwtRequestFilter jwtRequestFilter;

    @Autowired
    public SecurityConfiguration(RepositoryAwareUserDetailsService myUserDetailService,
                                 JwtRequestFilter jwtRequestFilter) {
        this.myUserDetailService = myUserDetailService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login", "/user", "/shop")
                .permitAll()

                .antMatchers(HttpMethod.GET, "/category", "/category/*",
                        "/subcategory", "/subcategory/*",
                        "/product", "/product/*", "/product/*/*",
                        "/image", "/image/*",
                        "/shop/*")
                .permitAll()

                .antMatchers(HttpMethod.GET,  "/swagger-resources/**",
                        "/swagger-ui.html",
                        "/v2/api-docs",
                        "/webjars/**",
                        "/csrf",
                        "/").permitAll()

                .antMatchers("/role", "/role/*")
                .denyAll()

                .antMatchers(HttpMethod.POST, "/category", "/subcategory")
                .hasAuthority(RoleEnum.ADMIN.name())
                .antMatchers(HttpMethod.PUT, "/category", "/subcategory")
                .hasAuthority(RoleEnum.ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/category/*", "/subcategory/*")
                .hasAuthority(RoleEnum.ADMIN.name())

                .antMatchers(HttpMethod.GET, "/bid", "/bid/*")
                .hasAnyAuthority(RoleEnum.ADMIN.name(), RoleEnum.SELLER.name(), RoleEnum.USER.name())
                .antMatchers(HttpMethod.POST, "/bid", "/bid/*")
                .hasAnyAuthority(RoleEnum.ADMIN.name(), RoleEnum.SELLER.name(), RoleEnum.USER.name())
                .antMatchers(HttpMethod.PUT, "/bid", "/bid/*")
                .denyAll()
                .antMatchers(HttpMethod.DELETE, "/bid", "/bid/*")
                .denyAll()


                .antMatchers(HttpMethod.POST, "/product")
                .hasAnyAuthority(RoleEnum.ADMIN.name(), RoleEnum.SELLER.name())
                .antMatchers(HttpMethod.PUT, "/product", "/product/*")
                .hasAnyAuthority(RoleEnum.ADMIN.name(), RoleEnum.SELLER.name())
                .antMatchers(HttpMethod.DELETE, "/product/*")
                .hasAnyAuthority(RoleEnum.ADMIN.name(), RoleEnum.SELLER.name())

                .antMatchers(HttpMethod.POST, "/image")
                .hasAnyAuthority(RoleEnum.ADMIN.name(), RoleEnum.SELLER.name(), RoleEnum.USER.name())
                .antMatchers(HttpMethod.PUT, "/image", "/image/*", "/user", "/user/*")
                .hasAnyAuthority(RoleEnum.ADMIN.name(), RoleEnum.SELLER.name(), RoleEnum.USER.name())
                .antMatchers(HttpMethod.DELETE, "/image", "/image/*")
                .hasAnyAuthority(RoleEnum.ADMIN.name(), RoleEnum.SELLER.name(), RoleEnum.USER.name())

                .antMatchers(HttpMethod.GET, "/user")
                .hasAuthority(RoleEnum.ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/user/*")
                .hasAuthority(RoleEnum.ADMIN.name())
                .antMatchers(HttpMethod.GET, "/user/*")
                .hasAnyAuthority(RoleEnum.ADMIN.name(), RoleEnum.SELLER.name(), RoleEnum.USER.name())


                .anyRequest()
                .denyAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    CorsConfigurationSource corsConfigurationSource()
    {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}

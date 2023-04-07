package com.springsecurity.security;

import com.springsecurity.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.jaas.memory.InMemoryConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig {

    @Autowired
    private PasswordConfig passwordConfig;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf().disable()
                .authorizeHttpRequests(request -> request.requestMatchers("/").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/**", "/management/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermissions())
                        .requestMatchers(HttpMethod.PUT, "/management/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermissions())
                        .requestMatchers(HttpMethod.DELETE, "/management/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermissions())
                        .requestMatchers(HttpMethod.GET, "/management/**").hasAnyRole(ApplicationUserRole.ADMIN.name(), ApplicationUserRole.STUDENT.name(),
                                ApplicationUserRole.ADMINISTRATION.name())
                        .anyRequest().authenticated()
                )
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic()
                .and().build();

    }

    @Bean
    public UserDetailsService userDetails() {
        UserDetails mohitUser = User.builder()
                .username("mohit")
                .password(passwordConfig.passwordEncoder().encode("mohit123"))
                .authorities(ApplicationUserRole.STUDENT.getPermissionAndRole())
//                .roles("STUDENT")
                .build();

        UserDetails amitUser = User.builder()
                .username("amit")
                .password(passwordConfig.passwordEncoder().encode("amit123"))
                .authorities(ApplicationUserRole.ADMIN.getPermissionAndRole())
//                .roles(ApplicationUserRole.ADMIN.name())
                .build();
        UserDetails defaultUser = User.builder()
                .username("default")
                .password(passwordConfig.passwordEncoder().encode("default123"))
                .authorities(ApplicationUserRole.ADMIN.getPermissionAndRole())
//                .roles(ApplicationUserRole.ADMIN.name())
                .build();
        return new InMemoryUserDetailsManager(
                mohitUser,
                amitUser,
                defaultUser
        );
    }

}

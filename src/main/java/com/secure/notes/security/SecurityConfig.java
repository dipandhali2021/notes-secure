package com.secure.notes.security;

import com.secure.notes.models.AppRole;
import com.secure.notes.models.Role;
import com.secure.notes.models.User;
import com.secure.notes.repositories.RoleRepository;
import com.secure.notes.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import java.time.LocalDate;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {


    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> {
            requests.requestMatchers("/api/admin/**").hasRole("ADMIN");
            (requests.anyRequest()).authenticated();
        });
        http.csrf(AbstractHttpConfigurer::disable);
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }


//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource) {
////        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
//        if(!manager.userExists("user1")){
//            manager.createUser(
//                    User.withUsername("user1")
//                            .password("{noop}password")
//                            .roles("USER")
//                            .build()
//            );
//        }
//        if(!manager.userExists("admin")){
//            manager.createUser(
//                    User.withUsername("admin")
//                            .password("{noop}password")
//                            .roles("ADMIN")
//                            .build()
//            );
//
//        }
//        return manager;
//    }


    @Bean
    public CommandLineRunner initData(RoleRepository roleRepository, UserRepository userRepository) {
        return args -> {
            Role userRole = roleRepository.findByRoleName(AppRole.ROLE_USER).orElseGet(() -> roleRepository.save(new Role(AppRole.ROLE_USER)));

            Role adminRole = roleRepository.findByRoleName(AppRole.ROLE_ADMIN).orElseGet(() -> roleRepository.save(new Role(AppRole.ROLE_ADMIN)));

            if (!userRepository.existsByUsername("user1")) {
                User user1 = new User("user1", "user1@gmail.com", "{noop}password");
                user1.setAccountNonLocked(false);
                user1.setAccountNonExpired(true);
                user1.setCredentialsNonExpired(true);
                user1.setEnabled(true);
                user1.setCredentialsExpiredDate(LocalDate.now().plusYears(1));
                user1.setAccountExpiredDate(LocalDate.now().plusYears(1));
                user1.setTwoFactorEnabled(false);
                user1.setSignUpMethod("email");
                user1.setRole(userRole);
                userRepository.save(user1);

            }

            if (!userRepository.existsByUsername("admin")) {
                User admin = new User("admin", "admin@gmail.com", "{noop}password");
                admin.setAccountNonLocked(false);
                admin.setAccountNonExpired(true);
                admin.setCredentialsNonExpired(true);
                admin.setEnabled(true);
                admin.setCredentialsExpiredDate(LocalDate.now().plusYears(1));
                admin.setAccountExpiredDate(LocalDate.now().plusYears(1));
                admin.setTwoFactorEnabled(false);
                admin.setSignUpMethod("email");
                admin.setRole(adminRole);
                userRepository.save(admin);
            }

        };
    }

}

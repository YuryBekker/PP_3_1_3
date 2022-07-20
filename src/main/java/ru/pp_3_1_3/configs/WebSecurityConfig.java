package ru.pp_3_1_3.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.pp_3_1_3.models.Role;
import ru.pp_3_1_3.models.User;
import ru.pp_3_1_3.repositories.RoleRepository;
import ru.pp_3_1_3.repositories.UserRepository;
import ru.pp_3_1_3.services.UService;
import ru.pp_3_1_3.services.UserServiceImp;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UService uService;
    private final SuccessUserHandler successUserHandler;

    public WebSecurityConfig(SuccessUserHandler successUserHandler, UService uService) {
        this.successUserHandler = successUserHandler;
        this.uService = uService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()

                .antMatchers("/").hasRole("ADMIN")
                .antMatchers("/profile").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedPage("/denied")
                .and()
                .formLogin().successHandler(successUserHandler)
                .permitAll()
                .and()
                .logout().logoutSuccessUrl("/login")
                .permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(UserServiceImp userServiceImp){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(uService);
        return provider;
    }
   /* @Bean
    //@Transactional
    public String adminInit(UserRepository userRepository, RoleRepository roleRepository){
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");
        User admin = new User(
                "admin",
                passwordEncoder().encode("12345"),
                "admin@gmail.com",
                Arrays.asList(roleAdmin,roleUser));
        roleRepository.save(roleAdmin);
        roleRepository.save(roleUser);
        userRepository.save(admin);
        return null;
    }*/
}

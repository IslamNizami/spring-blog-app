package itbrains.az.blogpage2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Security {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http
                .csrf(x->x.disable())
                .authorizeHttpRequests(request-> request
                                .requestMatchers("/admin/**").authenticated()
                                .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/admin")
                        .loginPage("/login")
                        .failureUrl("/login")
                );
        return http.build();
    }

    //@Bean
//    public UserDetailsService userDetailsService() throws Exception {
//        // ensure the passwords are encoded properly
//        User.UserBuilder users = User.withDefaultPasswordEncoder();
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(users.username("ferid@itbrains.edu.az").password("=$2a$10$XOaFTKKeVtqcQie7s3gss.ZoH3putnM71sYLB7FnoIX7dDmPy0fbu").roles("USER","ADMIN").build());
//        manager.createUser(users.username("gulnar").password("=$2a$10$XOaFTKKeVtqcQie7s3gss.ZoH3putnM71sYLB7FnoIX7dDmPy0fbu").roles("USER","ADMIN").build());
//        return manager;
//    }

}


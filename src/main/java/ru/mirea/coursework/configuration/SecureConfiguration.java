package ru.mirea.coursework.configuration;

import ru.mirea.coursework.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecureConfiguration extends WebSecurityConfigurerAdapter {

    private ClientService clientService;

    @Autowired
    private void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(clientService)
                .passwordEncoder(new BCryptPasswordEncoder())
        ;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .cors()
                .disable()
                .authorizeRequests()
                .antMatchers("/login", "/logout", "/reg", "/sign", "/css/**", "/script/**", "/").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin();
    }
}

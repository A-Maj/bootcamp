package com.bootcamp.bootcamp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        auth.jdbcAuthentication()
//                Poniżej koniecznie muszą byc 3 argumenty (mail, pass + 1)
                .usersByUsernameQuery("SELECT mail, password, 1 from user WHERE mail=?")
                .authoritiesByUsernameQuery("SELECT u.mail, r.role from user u inner join role r " + "on r.id=u.role_id WHERE u.mail=?")
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
//                    .antMatchers("/").permitAll()
//                    .anyRequest().authenticated()
                    .antMatchers("/admin/**", "/admin").hasAuthority("admin")
                    .antMatchers("/user/**", "/user").hasAuthority("user")
                    .antMatchers("/trainerr/**", "/trainerr").hasAuthority("trainer")
                    .anyRequest().permitAll()
                .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/")
                .and()
                .formLogin()
                    .loginPage("/logowanie")
                    .defaultSuccessUrl("/")
//                Ustawie nazwy pól przy logowaniu:
                    .usernameParameter("username")
                    .passwordParameter("password");


    }
}

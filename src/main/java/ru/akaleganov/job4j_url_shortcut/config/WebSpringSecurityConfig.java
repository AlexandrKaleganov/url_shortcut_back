package ru.akaleganov.job4j_url_shortcut.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSpringSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
                http
            .authorizeRequests()
                .antMatchers("/", "/home", "/index").permitAll()
                        .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout()
                .permitAll();
    }

//    /**
//     * @return {@link UsersDetailServiceCustom}  вернёт реализацию  интерфейса {@link UserDetailsService}
//     */
//    @Bean
//    public UsersDetailServiceCustom getUserDetailService() {
//        return new UsersDetailServiceCustom();
//    }

    /**
     * @param auth аутентификацию пользователей
     * @throws Exception возможное исключение (к примеру пользователь не найден)
     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(this.getUserDetailService()).passwordEncoder(this.passwordEncoder());
//    }

    /**
     * регистрация бина
     *
     * @return {@link PasswordEncoder} для хеширования паролей
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

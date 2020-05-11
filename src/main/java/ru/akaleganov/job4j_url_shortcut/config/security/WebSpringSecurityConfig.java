package ru.akaleganov.job4j_url_shortcut.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.akaleganov.job4j_url_shortcut.config.security.jwt.JwtAuthenticationEntryPoint;
import ru.akaleganov.job4j_url_shortcut.config.security.jwt.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * @return {@link UsersDetailServiceCustom}  вернёт реализацию  интерфейса {@link UserDetailsService}
     */
    @Bean
    public UsersDetailServiceCustom getUsersDetailServiceCustom() {
        return new UsersDetailServiceCustom();
    }

    @Bean
    public JwtAuthenticationFilter authenticationTokenFilterBean() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * @param auth аутентификацию пользователей
     * @throws Exception возможное исключение (к примеру пользователь не найден)
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.getUsersDetailServiceCustom()).passwordEncoder(this.bCryptPasswordEncoder());
    }

    @Bean
    public JwtAuthenticationEntryPoint unauthorizedHandler() {
        return new JwtAuthenticationEntryPoint();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/auth").permitAll()
                .antMatchers("/api/auth/registry").permitAll()
//                    .and().authorizeRequests().antMatchers("/user/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
//                    .and().authorizeRequests().antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }

}

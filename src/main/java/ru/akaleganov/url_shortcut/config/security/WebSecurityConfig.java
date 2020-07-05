package ru.akaleganov.url_shortcut.config.security;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.akaleganov.url_shortcut.config.security.jwt.JwtAuthenticationEntryPoint;
import ru.akaleganov.url_shortcut.config.security.jwt.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationFilter();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailServiceCustom).passwordEncoder(encoder());
    }

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private UserDetailServiceCustom userDetailServiceCustom;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/auth").permitAll()
                .antMatchers("/api/auth/registry").permitAll()
                .antMatchers("/api/auth/roles/").authenticated()
                .antMatchers("/api/roles").authenticated()
                .antMatchers("/api/getUrlByShortCut/**").permitAll()
                .antMatchers("/api/**").authenticated()
//                .antMatchers(HttpMethod.GET,"/constructor/**").permitAll()
                .antMatchers(HttpMethod.GET, "/").permitAll()
                .antMatchers("/api/users/**").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/api/url/**").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/api/url").hasAnyAuthority("ADMIN", "USER")
//                    .and().authorizeRequests().antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }

}

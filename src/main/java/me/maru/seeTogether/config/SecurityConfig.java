package me.maru.seeTogether.config;

import me.maru.seeTogether.jwt.AuthProvider;
import me.maru.seeTogether.jwt.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 스프링 시큐리티 클래스 <p>
 *
 * @author 이석민
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthProvider authProvider;
    private final AuthenticationEntryPointImpl authenticationEntryPoint;

    @Autowired
    public SecurityConfig(AuthProvider authProvider, AuthenticationEntryPointImpl authenticationEntryPoint) {
        this.authProvider = authProvider;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }


    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()

                    .exceptionHandling()
                    .authenticationEntryPoint(authenticationEntryPoint)

                .and()
                    .authorizeRequests()
                    .antMatchers("/api/health-ok").permitAll()
                    .antMatchers("/api/v1/login").permitAll()
                    .anyRequest().authenticated()

                // exception handling 할 때 우리가 만든 클래스를 추가
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                    .addFilterBefore(new JwtFilter(authProvider), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

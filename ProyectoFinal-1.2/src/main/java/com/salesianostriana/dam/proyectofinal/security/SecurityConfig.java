package com.salesianostriana.dam.proyectofinal.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final UserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;
	private final RoleBasedSuccessHandler successHandler;
	
	@Bean
	public AuthenticationManager 
			authenticationManager(HttpSecurity http) throws Exception {
		
		AuthenticationManagerBuilder authBuilder =
				http.getSharedObject(AuthenticationManagerBuilder.class);
		
		return authBuilder
			.authenticationProvider(daoAuthenticationProvider())
			.build();
		
		
	}
	
	@Bean 
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder);
		return provider;
	}

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .antMatchers("/css/**", "/js/**", "/webjars/**", "/h2-console/**").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin(login -> login
                        .loginPage("/login")
                        //.defaultSuccessUrl("/web/index", true)
                        .successHandler(successHandler)
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .permitAll()
                        .logoutSuccessUrl("/"));
			

		// Añadimos esto para poder seguir accediendo a la consola de H2
		// con Spring Security habilitado.
		http.csrf().disable();
        http.headers(headers -> headers.frameOptions().disable());
		
		return http.build();
	}

}

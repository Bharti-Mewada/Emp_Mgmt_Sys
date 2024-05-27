package com.project.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.project.security.JwtAuthenticationEntryPoint;
import com.project.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	 private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	    private final JwtAuthenticationFilter jwtAuthenticationFilter;

	    public SecurityConfiguration(@Lazy JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,  @Lazy JwtAuthenticationFilter jwtAuthenticationFilter) {
	        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
	        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	    }
	

	
	 @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	            .csrf(csrf -> csrf.disable())
	            .authorizeHttpRequests(authorizeRequests -> authorizeRequests
	                .requestMatchers("/public/**").permitAll()
	                .requestMatchers("/auth/login").permitAll()
	                .requestMatchers("/emp/**").hasRole("ADMIN")
	                .anyRequest().authenticated()
	            )
	            .exceptionHandling(exceptionHandling -> 
	                exceptionHandling.authenticationEntryPoint(jwtAuthenticationEntryPoint)
	            )
	            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

	        return http.build();
	    }

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails adminUserdetail = User.builder().username("Bharti").password(passwordEncoder().encode("bharti@123"))
				.roles("ADMIN").build();
		UserDetails localuserDetail = User.builder().username("Tripti").password(passwordEncoder().encode("tripti@123"))
				.roles("USER").build();
		return new InMemoryUserDetailsManager(adminUserdetail, localuserDetail);
	}
	
	

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	 @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	        return authenticationConfiguration.getAuthenticationManager();
	    }

}

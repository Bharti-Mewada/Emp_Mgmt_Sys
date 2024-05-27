package com.project.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.AuthenticationException;

import com.project.security.JwtHelper;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtHelper jwtHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@PostMapping("/login")
	public Map<String, String> login(@RequestBody Map<String,String> loginData){
		String username= loginData.get("username");
		//String password= loginData.get("password");
		 try {
//	            Authentication authentication = authenticationManager.authenticate(
//	                    new UsernamePasswordAuthenticationToken(username, password));
	            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
	            String token = jwtHelper.generateToken(userDetails);
	            Map<String, String> response = new HashMap<>();
	            response.put("token", token);
	            return response;
	        } catch (AuthenticationException e) {
	            throw new RuntimeException("Invalid credentials");
	        }
	}

}

package com.appsurtidos.backend.controllers;

import com.appsurtidos.backend.dto.UserLoginDTO;
import com.appsurtidos.backend.dto.UserRegisterDTO;
import com.appsurtidos.backend.security.JwtUtil;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.appsurtidos.backend.service.UserRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    // private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final UserRegistrationService userRegistrationService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(JwtUtil jwtUtil, UserDetailsService userDetailsService, UserRegistrationService userRegistrationService, PasswordEncoder passwordEncoder) {
        // this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.userRegistrationService = userRegistrationService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLoginDTO user) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());

        if (!passwordEncoder.matches(user.getPassword(), userDetails.getPassword())) {
            throw new BadCredentialsException("Incorrect password");
        }
        return jwtUtil.generateToken(userDetails.getUsername());
        // authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        // UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        // return jwtUtil.generateToken(userDetails.getUsername());
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterDTO userDTO) {
        try {
            userRegistrationService.registerUser(userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail());
            return ResponseEntity.ok("User registered successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
}

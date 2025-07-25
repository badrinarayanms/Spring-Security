package com.badri.SpringSecurity.controller;

import com.badri.SpringSecurity.model.User;
import com.badri.SpringSecurity.service.JwtService;
import com.badri.SpringSecurity.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;
    @PostMapping("register")
    public User register(@RequestBody User user){


        return userService.saveuser(user);
    }

    @PostMapping("login")
    public String login(@RequestBody User user, HttpServletResponse response){
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));

        if (authentication.isAuthenticated()){
            String token = jwtService.generatetoken(user.getUsername());
            // ✅ Set token as HttpOnly cookie
            Cookie cookie = new Cookie("jwt", token);
            cookie.setHttpOnly(true);         // Cannot be accessed via JavaScript
            cookie.setSecure(false);          // Set to true in production (HTTPS only)
            cookie.setPath("/");              // Send for all requests
            cookie.setMaxAge(24 * 60 * 60);   // 1 day expiry

            response.addCookie(cookie);

            return "Login successful";
        }
        return "Invalid credentials";
    }



    @GetMapping("/me")
    public Map<String, String> getLoggedInUser(Authentication authentication) {
        UserDetails user = (UserDetails) authentication.getPrincipal();

        // For demo only — showing password (not secure in real apps!)
        return Map.of(
                "username", user.getUsername(),
                "password", user.getPassword()
        );
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("jwt", "");
        cookie.setHttpOnly(true);
        cookie.setSecure(false); // true in prod
        cookie.setPath("/");
        cookie.setMaxAge(0); // Expire immediately
        response.addCookie(cookie);

        return ResponseEntity.ok(Map.of("message", "Logout successful"));
    }

}
//eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiYWRyaSIsImlhdCI6MTc1MzI5MDg0MCwiZXhwIjoxNzUzMjkxMDIwfQ.K5M5LNJKwpqVVIySkVs0deWpwJbQvfYqyfvDuZmqPxA

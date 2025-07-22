package com.badri.SpringSecurity.service;

import com.badri.SpringSecurity.model.User;
import com.badri.SpringSecurity.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

    public User saveuser(User user){
        System.out.println("Registering user: " + user.getUsername());
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }
}

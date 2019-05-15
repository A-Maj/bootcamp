package com.bootcamp.bootcamp.service;

import com.bootcamp.bootcamp.model.User;
import com.bootcamp.bootcamp.repository.RoleRepository;
import com.bootcamp.bootcamp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public void save(User user) {

        user.setRole(roleRepository.findByRole("user"));
//        user.setRole(roleRepository.findById(2));

        PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userRepository.save(user);
    }

    public void saveUser(User user) {

        user.setEditions(null);

        PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userRepository.save(user);
    }

}

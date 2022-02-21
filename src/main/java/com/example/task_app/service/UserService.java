package com.example.task_app.service;

import com.example.task_app.repository.User;
import com.example.task_app.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
  
  @Autowired
  private UserRepository userRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Transactional
  public User save(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }
}

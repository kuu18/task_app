package com.example.task_app.service;

import com.example.task_app.repository.User;
import com.example.task_app.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User loginUser = userRepository.findByUsername(username);

      // ユーザが存在しない場合
      if (loginUser == null) throw new UsernameNotFoundException("ユーザーが存在しません");
      return loginUser;
    }
}

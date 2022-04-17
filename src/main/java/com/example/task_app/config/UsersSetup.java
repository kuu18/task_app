package com.example.task_app.config;

import com.example.task_app.service.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@Configuration
public class UsersSetup {

  @Autowired
  private UserDetailsServiceImpl userDetailsService;

  @Autowired
  private WebSecurityConfig webSecurityConfig;
  
  @Autowired
  public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
    //UserDetailsServiceを設定してDaoAuthenticationProviderを有効化する
    auth.userDetailsService(userDetailsService)
    //エンコードを設定しハッシュ化する
    .passwordEncoder(webSecurityConfig.passwordEncoder());
  }
  
}

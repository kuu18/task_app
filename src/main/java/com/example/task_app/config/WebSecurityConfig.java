package com.example.task_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
    public void configure(WebSecurity web) throws Exception {
      web
        .ignoring()
        .antMatchers("/resources/**", "/static/**","/webjars/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
        //home画面とログイン画面、新規登録画面を除外
        .antMatchers("/loginform", "/signupform", "/signup", "/home", "/")
        .permitAll()
        //全リクエスト認証対象
        .anyRequest().authenticated()
        .and()
        //フォームでのログイン
        .formLogin()
        .loginPage("/loginform")
        .loginProcessingUrl("/login")
        .usernameParameter("username")
        .passwordParameter("password")
        //認証成功後のURL
        .defaultSuccessUrl("/task/management", true)
        .and()
        .logout()
        .logoutUrl("/logout")
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/loginform?logout")
        .invalidateHttpSession(true)
        .permitAll()
        .deleteCookies("JSESSIONID");

  }

}

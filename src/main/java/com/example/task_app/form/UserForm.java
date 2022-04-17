package com.example.task_app.form;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class UserForm {
  @NotBlank
  private String username;
  @NotBlank
  @Length(min=8, max=255)
  private String password;

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}

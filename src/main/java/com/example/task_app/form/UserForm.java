package com.example.task_app.form;

import javax.validation.constraints.NotEmpty;

public class UserForm {
  @NotEmpty
  private String username;
  @NotEmpty
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

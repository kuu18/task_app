package com.example.task_app.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserForm {
  @NotEmpty
  private String username;
  @NotEmpty
  @Size(min=8, max=255)
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

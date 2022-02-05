package com.example.task_app.form;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class TaskForm {
  @NotNull(groups = UpdateDeleteGroup.class)
  private Integer taskId;
  @NotNull(groups = CreateGroup.class)
  private String name;
  private String content;
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) 
  private LocalDateTime deadLine;
  private Boolean status = false;

  public Integer getTaskId() {
    return this.taskId;
  }

  public void setTaskId(Integer taskId) {
    this.taskId = taskId;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getContent() {
    return this.content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public LocalDateTime getDeadLine() {
    return this.deadLine;
  }

  public void setDeadLine(LocalDateTime deadLine) {
    this.deadLine = deadLine;
  }

  public Boolean getStatus() {
    return this.status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }

  public interface UpdateDeleteGroup {
	}
	public interface CreateGroup {
	}

}

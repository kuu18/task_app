package com.example.task_app.form;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class TaskForm {
  @NotNull(groups = UpdateDeleteGroup.class)
  private Integer taskId;
  @NotNull(groups = CreateGroup.class)
  @Size(max = 50, groups = {CreateGroup.class, UpdateDeleteGroup.class})
  private String name;
  private String content;
  @NotNull(groups = CreateGroup.class)
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) 
  private LocalDateTime deadLine;
  private Boolean status = false;
  private Integer userid;

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

  public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

  public interface UpdateDeleteGroup {
	}
	public interface CreateGroup {
	}

}

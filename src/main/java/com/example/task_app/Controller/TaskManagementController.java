package com.example.task_app.Controller;

import com.example.task_app.repository.Task;
import com.example.task_app.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/task")
public class TaskManagementController {
  @Autowired
  private TaskService service;
  
  @RequestMapping(value = "/management")
  public ModelAndView output(ModelAndView mav) {
    List<Task> list = service.findAll();
    List<Task> latestTaskList = service.findLatestTask(); 
    // セッション情報への登録
		mav.addObject("list", list);
    mav.addObject("latestTaskList", latestTaskList);
    // ビュー名の設定
    mav.setViewName("/taskmanagement");
    return mav;
  }
}

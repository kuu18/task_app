package com.example.task_app.Controller;

import com.example.task_app.repository.User;
import com.example.task_app.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
  @Autowired
  private TaskService taskService;

  /**
	 * Homeへの遷移アクション
	 *
	 * @param 
	 * @return mav
	 */

  @RequestMapping(value = { "/", "/home" })
  public ModelAndView taskForm(ModelAndView mav, @AuthenticationPrincipal User currentUser){
    if (currentUser != null){
      mav.addObject("list", taskService.findAll(currentUser.getUserid()));
      mav.addObject("latestTaskList", taskService.findLatestTask(currentUser.getUserid()));
      mav.setViewName("taskmanagement");
    }
    else {
      mav.setViewName("home");
    }
    return mav;
  }
}

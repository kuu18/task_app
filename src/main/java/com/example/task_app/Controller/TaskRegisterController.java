package com.example.task_app.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/task")
public class TaskRegisterController {
  @RequestMapping(value = "/input")
  public ModelAndView taskRegister(ModelAndView mav){
    mav.setViewName("/taskinput");
    return mav;
  }  
}

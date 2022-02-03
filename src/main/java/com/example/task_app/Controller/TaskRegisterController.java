package com.example.task_app.Controller;

import com.example.task_app.form.TaskForm;
import com.example.task_app.repository.Task;
import com.example.task_app.service.TaskService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/task")
public class TaskRegisterController {
  @Autowired
  private TaskService taskService;

  @RequestMapping(value = "/form")
  public ModelAndView taskInput(ModelAndView mav, @ModelAttribute("form") TaskForm form){
    mav.setViewName("/taskform");
    return mav;
  }
  
  @RequestMapping(value = "/register")
  public ModelAndView taskRegister(ModelAndView mav, @ModelAttribute("form") @Validated TaskForm form, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
			// ビュー名の設定
			mav.setViewName("/taskform");
		} else {
			Task task = new Task();
			BeanUtils.copyProperties(form, task);
			// insert
			taskService.save(task);
			// セッション情報への登録
			mav.addObject("list", taskService.findAll());
      mav.addObject("latestTaskList", taskService.findLatestTask());
			// ビュー名の設定
			mav.setViewName("taskmanagement");
		}
    return mav;
  }

}

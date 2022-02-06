package com.example.task_app.Controller;

import com.example.task_app.repository.Task;
import com.example.task_app.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/task")
public class TaskManagementController {
  @Autowired
  private TaskService service;

  /**
	 * タスク管理画面への遷移アクション
	 *
	 * @param 
	 * @return mav
	 */
  
  @RequestMapping(value = "/management")
  public ModelAndView output(ModelAndView mav, @RequestParam(name = "nameKey") Optional<String> namekey) {
    List<Task> list = service.findByNameLike(namekey);
    if (!namekey.isPresent()) {
      // 直近のタスク取得
      List<Task> latestTaskList = service.findLatestTask();
      // セッション情報への登録
      mav.addObject("latestTaskList", latestTaskList);
    }
    // セッション情報への登録
		mav.addObject("list", list);
    // ビュー名の設定
    mav.setViewName("/taskmanagement");
    return mav;
  }
}

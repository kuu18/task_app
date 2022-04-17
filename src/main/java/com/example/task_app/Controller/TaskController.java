package com.example.task_app.Controller;

import java.util.List;
import java.util.Optional;

import com.example.task_app.form.TaskForm;
import com.example.task_app.repository.Task;
import com.example.task_app.repository.User;
import com.example.task_app.service.TaskService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/task")
public class TaskController {
  @Autowired
  private TaskService taskService;

    /**
	 * タスク管理画面への遷移アクション
	 *
	 * @param 
	 * @return mav
	 */
  
  @RequestMapping(value = "/management")
  public ModelAndView output(ModelAndView mav, @RequestParam(name = "nameKey") Optional<String> namekey, @AuthenticationPrincipal User currentUser) {
    List<Task> list = taskService.findByNameLike(namekey, currentUser.getUserid());
    if (!namekey.isPresent()) {
      // 直近のタスク取得
      List<Task> latestTaskList = taskService.findLatestTask(currentUser.getUserid());
      // セッション情報への登録
      mav.addObject("latestTaskList", latestTaskList);
			// セッション情報への登録
			mav.addObject("list", list);
    }
		else{
			// セッション情報への登録
			mav.addObject("searchList", list);
		}
    // ビュー名の設定
    mav.setViewName("taskmanagement");
    return mav;
  }

  /**
	 * 入力画面への遷移アクション
	 *
	 * @param 
	 * @return mav
	 */

  @RequestMapping(value = "/form")
  public ModelAndView taskForm(ModelAndView mav, @ModelAttribute("form") TaskForm form, @AuthenticationPrincipal User currentUser){
    mav.addObject("currentUser", currentUser);
    mav.setViewName("taskform");
    return mav;
  }

  /**
	 * 入力画面からの遷移アクション（登録）
	 *
	 * @param 
	 * @return mav
	 */

  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public ModelAndView taskRegister(ModelAndView mav, @ModelAttribute("form") @Validated(TaskForm.CreateGroup.class) TaskForm form, BindingResult bindingResult, @AuthenticationPrincipal User currentUser) {
    if (bindingResult.hasErrors()) {
			// 現在のユーザーの登録
			mav.addObject("currentUser", currentUser);
			// ビュー名の設定
			mav.setViewName("taskform");
		} else {
			Task task = new Task();
			BeanUtils.copyProperties(form, task);
			// insert
			taskService.save(task);
			// セッション情報への登録
			mav.addObject("list", taskService.findAll(currentUser.getUserid()));
      mav.addObject("latestTaskList", taskService.findLatestTask(currentUser.getUserid()));
			// ビュー名の設定
			mav.setViewName("taskmanagement");
		}
    return mav;
  }

  /**
	 * 入力画面への遷移アクション
	 *
	 * @param 
	 * @return mav
 	 */
	@RequestMapping(value = "/form/{taskId}", method = RequestMethod.POST)
	public ModelAndView taskUpdateForm(ModelAndView mav,
			@ModelAttribute("form") @Validated(TaskForm.UpdateDeleteGroup.class) TaskForm form,
      @AuthenticationPrincipal User currentUser,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			// セッション情報への登録
			mav.addObject("list", taskService.findAll(currentUser.getUserid()));
      mav.addObject("latestTaskList", taskService.findLatestTask(currentUser.getUserid()));
			// ビュー名の設定
			mav.setViewName("taskmanagement");
		} else {
			// 選択レコードの現在値を取得
			BeanUtils.copyProperties(taskService.findOne(form.getTaskId()), form);
      //　セッション情報への登録
      mav.addObject("currentUser", currentUser);
			// ビュー名の設定
			mav.setViewName("taskupdateform");
		}
		return mav;
	}

  /**
	 * 入力画面からの遷移アクション（更新）
	 *
	 * @param 
	 * @return mav
	 */

  @RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(ModelAndView mav, @ModelAttribute("form") @Validated(TaskForm.CreateGroup.class) TaskForm form, BindingResult bindingResult, @AuthenticationPrincipal User currentUser) {
		if (bindingResult.hasErrors()) {
			// 現在のユーザーの登録
			mav.addObject("currentUser", currentUser);
			// ビュー名の設定
			mav.setViewName("taskupdateform");
		} else {
			Task task = new Task();
			BeanUtils.copyProperties(form, task);
			// update
			taskService.update(task);
			// セッション情報への登録
			mav.addObject("list", taskService.findAll(currentUser.getUserid()));
      mav.addObject("latestTaskList", taskService.findLatestTask(currentUser.getUserid()));
			// ビュー名の設定
			mav.setViewName("taskmanagement");
		}
		return mav;
	}

  /**
	 * ステータスの更新
	 *
	 * @param 
	 * @return mav
	 */

  @RequestMapping(value = "/statusupdate/{taskId}", method = RequestMethod.POST)
	public ModelAndView updateStatus(ModelAndView mav,
			@PathVariable("taskId") Integer taskId,
      @AuthenticationPrincipal User currentUser,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			// ビュー名の設定
			mav.setViewName("taskmanagement");
		} else {
			// update
			taskService.updateStatus(taskId);
			// セッション情報への登録
			mav.addObject("list", taskService.findAll(currentUser.getUserid()));
      mav.addObject("latestTaskList", taskService.findLatestTask(currentUser.getUserid()));
			// ビュー名の設定
			mav.setViewName("taskmanagement");
		}
		return mav;
	}

  /**
	 * タスクの削除
	 *
	 * @param 
	 * @return mav
	 */
  @RequestMapping(value = "/delete/{taskId}", method = RequestMethod.POST)
	public ModelAndView delete(ModelAndView mav,
			@ModelAttribute("form") @Validated(TaskForm.UpdateDeleteGroup.class) TaskForm form,
      @AuthenticationPrincipal User currentUser,
			BindingResult bindingResult) {
		if (!bindingResult.hasErrors()) {
			taskService.delete(form.getTaskId());
		}
		// セッション情報への登録
		mav.addObject("list", taskService.findAll(currentUser.getUserid()));
    mav.addObject("latestTaskList", taskService.findLatestTask(currentUser.getUserid()));
		// ビュー名の設定
		mav.setViewName("taskmanagement");
		return mav;
	}
}

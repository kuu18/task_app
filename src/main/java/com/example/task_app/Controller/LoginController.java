package com.example.task_app.controller;

import com.example.task_app.form.UserForm;
import com.example.task_app.repository.User;
import com.example.task_app.service.UserService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
  @Autowired
  private UserService userService;
  /**
	 * 新規登録画面への遷移アクション
	 *
	 * @param 
	 * @return mav
	 */

  @RequestMapping(value = "/signupform")
  public ModelAndView taskForm(ModelAndView mav, @ModelAttribute("form") UserForm form){
    mav.setViewName("/signup");
    return mav;
  }

  /**
	 * 新規登録画面からの遷移アクション（登録）
	 *
	 * @param 
	 * @return mav
	 */

  @RequestMapping(value = "/signup", method = RequestMethod.POST)
  public ModelAndView taskRegister(ModelAndView mav, @ModelAttribute("form") @Validated UserForm form,
		BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
			// ビュー名の設定
			mav.setViewName("/signup");
		} else {
			User user = new User();
      BeanUtils.copyProperties(form, user);
			// insert
			userService.save(user);
			mav.addObject("msg", "ユーザーを登録しましたログインしてください");
			// ビュー名の設定
			mav.setViewName("login");
		}
    return mav;
  }

  /**
	 * ログイン画面への遷移アクション
	 *
	 * @param 
	 * @return mav
	 */

  @RequestMapping(value = "/loginform", method = RequestMethod.GET)
  public ModelAndView loginForm(ModelAndView mav, @ModelAttribute("form") UserForm form){
    mav.setViewName("/login");
    return mav;
  }
}

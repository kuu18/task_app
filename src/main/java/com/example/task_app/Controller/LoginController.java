package com.example.task_app.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.example.task_app.form.UserForm;
import com.example.task_app.repository.User;
import com.example.task_app.service.UserService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    mav.setViewName("signup");
    return mav;
  }

  /**
	 * 新規登録画面からの遷移アクション（登録）
	 *
	 * @param 
	 * @return mav
	 */

  @RequestMapping(value = "/signup", method = RequestMethod.POST)
  public ModelAndView userRegister(ModelAndView mav, @ModelAttribute("form") @Validated UserForm form,
		BindingResult bindingResult, HttpServletRequest request) {
    if (bindingResult.hasErrors()) {
			// ビュー名の設定
			mav.setViewName("signup");
		} else {
			User user = new User();
      BeanUtils.copyProperties(form, user);
			try{
				// insert
				userService.save(user);
				//welcomeメッセージ
				mav.addObject("msg", "Welcom to TaskApp!!");
				// ビュー名の設定
				mav.setViewName("taskmanagement");
			}catch(DataIntegrityViolationException e){
				mav.addObject("msg", "ユーザー名が既に存在しています。");
				// ビュー名の設定
				mav.setViewName("signup");
			}
			//ユーザー登録後ログイン処理
			try {
				request.login(form.getUsername(), form.getPassword());
			} catch (ServletException e) {
					e.printStackTrace();
			}
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
    mav.setViewName("login");
    return mav;
  }
}

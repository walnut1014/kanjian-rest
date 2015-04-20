package name.walnut.controller.passport;

import java.util.Map;

import javax.servlet.http.HttpSession;

import name.walnut.auth.entity.AuthAccount;
import name.walnut.auth.service.PassportService;
import name.walnut.common.BusinessException;
import name.walnut.web.vo.Normal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/passport")
public class LoginController {
	
	@RequestMapping(value="login", method = RequestMethod.POST)
	public Normal login(@RequestBody AuthAccount authAccount, HttpSession session) {
		
		session.setAttribute(Const.CURRENT_AUTH, passportService.login(
													authAccount.getMobilephone(), 
													authAccount.getPassword()));
		return Normal.INSTANCE;
	}
	
	@RequestMapping(value="forgotPassword/sendCode", method = RequestMethod.POST)
	public Normal forgotPasswordCode(@RequestBody AuthAccount authAccount, HttpSession session) {
		
		if(!passportService.isExist(authAccount.getMobilephone()))
			throw new BusinessException("此用户已不存在");
		
		session.setAttribute(Const.MOBILEPHONE, authAccount.getMobilephone());
		return Normal.INSTANCE;
	}
	
	@RequestMapping(value="setPassword", method = RequestMethod.POST)
	public Normal setNewPassword(HttpSession session, @RequestBody Map<String, String> param) {
		
		if(!param.get("token").equals(session.getAttribute(Const.MESSAGE_TOKEN)))
			throw new BusinessException("注册时非法调用");
		
		passportService.setPassword((String)session.getAttribute(Const.MOBILEPHONE), 
										param.get("password"));
		return Normal.INSTANCE;
	}
	
	
	@Autowired
	private PassportService passportService;
}

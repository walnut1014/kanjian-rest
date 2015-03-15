package name.walnut.controller.passport;

import javax.servlet.http.HttpSession;

import name.walnut.auth.entity.AuthAccount;
import name.walnut.auth.service.AuthAccountService;
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
	public Normal login(@RequestBody AuthAccount authAccount) {
		
		//authAccountService.login(authAccount.getMobilephone(), authAccount.getPassword());
		return Normal.INSTANCE;
	}
	
	@RequestMapping(value="forgotPassword/sendCode", method = RequestMethod.POST)
	public Normal forgotPasswordCode(@RequestBody AuthAccount authAccount, HttpSession session) {
		
		authAccountService.isExist(authAccount.getMobilephone());
		
		session.setAttribute(FORGOT_PASSWORD_CODE, "123456");
		return Normal.INSTANCE;
	}
	
	
	@Autowired
	private AuthAccountService authAccountService;
	
	private final static String FORGOT_PASSWORD_CODE = "forgotPasswordCode";
}

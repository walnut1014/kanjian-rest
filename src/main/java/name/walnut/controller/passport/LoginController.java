package name.walnut.controller.passport;

import java.util.Map;

import javax.servlet.http.HttpSession;

import name.walnut.auth.dto.OnlineUser;
import name.walnut.auth.service.PassportService;
import name.walnut.common.BusinessException;
import name.walnut.controller.Const;
import name.walnut.controller.passport.vo.LoginParam;
import name.walnut.controller.utils.OnlineUtils;
import name.walnut.web.vo.Normal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/passport")
public class LoginController {
	
	@RequestMapping(value="login", method = RequestMethod.POST)
	public OnlineUser login(@RequestBody LoginParam loginParam) {
		
		return OnlineUtils.login(loginParam);
	}
	
	@RequestMapping(value="setPassword", method = RequestMethod.POST)
	public Normal setNewPassword(HttpSession session, @RequestBody Map<String, String> param) {
		
		if(!param.get("token").equals(session.getAttribute(Const.MESSAGE_TOKEN)))
			throw new BusinessException("注册时非法调用");
		
		passportService.setPassword((String)session.getAttribute(Const.MOBILEPHONE), 
										param.get("password"));
		return Normal.INSTANCE;
	}
	
	@RequestMapping(value="forgotPassword/sendCode", method=RequestMethod.GET)
	public Normal forgetPasswordSendCode(@RequestParam("mobilephone") String mobilephone, 
									   HttpSession session) {
		
		if(!passportService.isExist(mobilephone))
			throw new BusinessException("此用户不存在");
		
		session.setAttribute(Const.MOBILEPHONE, mobilephone);
		return Normal.INSTANCE;
	}
	
	/**
	 * 判断是否登陆
	 * @return
	 */
	@RequestMapping(value="isLogin", method=RequestMethod.GET)
	public boolean isLong() {
		return OnlineUtils.isLogin();
	}
	
	
	@Autowired
	private PassportService passportService;
}

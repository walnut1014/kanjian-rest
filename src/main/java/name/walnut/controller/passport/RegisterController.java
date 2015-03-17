package name.walnut.controller.passport;

import java.util.Map;

import javax.servlet.http.HttpSession;

import name.walnut.auth.entity.AuthAccount;
import name.walnut.auth.service.AuthAccountService;
import name.walnut.controller.vo.RegisterVo;
import name.walnut.web.vo.Normal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/passport")
public class RegisterController {

	@RequestMapping(value="register", method=RequestMethod.POST)
	public Normal register(HttpSession session, @RequestBody AuthAccount authAccount) {
		
		authAccount.setMobilephone(((RegisterVo) session.getAttribute("veriCode")).getMobilephone());
		authAccountService.register(authAccount);
		return Normal.INSTANCE;
	}
	
	@RequestMapping(value="register/veriCode", method=RequestMethod.POST)
	public boolean getVeriCode(@RequestBody Map<String, String> param, HttpSession session) {
		
		String code = ((RegisterVo) session.getAttribute("veriCode")).getVeriCode();
		return param.get("code").equals(code);
	}
	
	@RequestMapping(value="register/sendCode", method=RequestMethod.POST)
	public Normal sendCode(@RequestBody AuthAccount authAccount, HttpSession session) {
		
		authAccountService.isExist(authAccount.getMobilephone());
		
		session.setAttribute("veriCode", new RegisterVo(authAccount.getMobilephone(), "123456"));
		return Normal.INSTANCE;
	}
	
	
	@Autowired
	private AuthAccountService authAccountService;
}

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
public class RegisterController {

	@RequestMapping(value="register", method=RequestMethod.POST)
	public Normal register(HttpSession session) {
		
		System.out.println(session.getAttribute("veriCode"));
		return Normal.INSTANCE;
	}
	
	@RequestMapping(value="register/veriCode", method=RequestMethod.POST)
	public Normal getVeriCode(@RequestBody AuthAccount authAccount, HttpSession session) {
		
		session.setAttribute("veriCode", 123456);
		return Normal.INSTANCE;
	}
	
	
	@Autowired
	private AuthAccountService authAccountService;
}

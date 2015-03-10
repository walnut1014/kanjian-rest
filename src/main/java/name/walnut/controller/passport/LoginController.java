package name.walnut.controller.passport;

import name.walnut.auth.entity.AuthAccount;
import name.walnut.auth.service.AuthAccountService;
import name.walnut.web.vo.Normal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	
	@RequestMapping(value="/passport/login", method = RequestMethod.POST)
	public Normal login(@RequestBody AuthAccount authAccount) {
		
		System.out.println(authAccount);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Normal.INSTANCE;
	}
	
	@Autowired
	private AuthAccountService authAccountService;
}

package name.walnut.auth.service.impl;

import java.util.Date;

import name.walnut.auth.entity.AuthAccount;
import name.walnut.auth.entity.User;
import name.walnut.auth.service.PassportService;
import name.walnut.test.TestSupport;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class PassportServiceImplTest extends TestSupport{

	@Test
	public void testRegister() {
		
		AuthAccount auth = new AuthAccount();
		auth.setCreateDate(new Date());
		auth.setMobilephone("123123123");
		
		User user = new User();
		user.setNickName("sdfsdf");
		passportService.register(auth, user);
	}
	
	@Autowired
	private PassportService passportService;

}

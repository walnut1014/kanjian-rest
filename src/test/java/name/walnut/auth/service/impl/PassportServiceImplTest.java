package name.walnut.auth.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import name.walnut.auth.entity.User;
import name.walnut.auth.service.PassportService;
import name.walnut.test.TestSupport;


public class PassportServiceImplTest extends TestSupport{

	@Test
	public void testRegister() {
//		insert("13000000001", "测试二");
//		insert("13000000002", "测试三");
//		insert("13000000003", "测试四");
//		insert("13000000004", "测试五");
		User user = new User();
		user.setMobilephone("13000000001");
		user.setNickName("测试一");
		user.setHeadPhotoPath("adsfdsaf");
		passportService.register(user);
	}
	
	
	@Autowired
	private PassportService passportService;

}

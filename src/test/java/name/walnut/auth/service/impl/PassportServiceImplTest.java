package name.walnut.auth.service.impl;

import java.util.Date;

import name.walnut.auth.entity.AuthAccount;
import name.walnut.auth.entity.User;
import name.walnut.auth.service.PassportService;
import name.walnut.test.TestSupport;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class PassportServiceImplTest extends TestSupport{

	@Test
	public void testRegister() {
		insert("13000000001", "测试二");
		insert("13000000002", "测试三");
		insert("13000000003", "测试四");
		insert("13000000004", "测试五");
	}
	
	private void insert(String phone, String nickName) {
		
		AuthAccount auth = new AuthAccount();
		auth.setCreateDate(new Date());
		auth.setMobilephone(phone);
		
		User user = new User();
		user.setNickName(nickName);
		user.setHeadPhotoPath(auth.getMobilephone()+"_head");
		passportService.register(auth, user);
	}
	
	@Autowired
	private PassportService passportService;

}

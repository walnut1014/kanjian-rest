package name.walnut.controller.common;

import name.walnut.auth.dto.OnlineUser;
import name.walnut.auth.dto.UserQueryResult;
import name.walnut.auth.service.UserService;
import name.walnut.controller.utils.OnlineUtils;
import name.walnut.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
	
	@RequestMapping(params="mobilephone", method = RequestMethod.GET)
	public List<UserQueryResult> findUserByMobile(@RequestParam(required=true, value="mobilephone") 
									String mobilephone) {
		return userService.findUserByMobile(mobilephone);
	}
	
	@RequestMapping(params="mobilephones", method = RequestMethod.GET)
	public List<UserQueryResult> findUserByMobiles(String mobilephones) {
		
		if(StringUtils.isBlank(mobilephones))
			return new ArrayList<>();
		return userService.findUserByMobiles(mobilephones.split(","));
	}
	
	@RequestMapping(value="current", method = RequestMethod.GET)
	public OnlineUser getCurrentUser() {
		return OnlineUtils.getOnlineAuth();
	}
	
//	@RequestMapping(value="test")
//	public void testTranstr() {
//		userService.test();
//	}
	
	public boolean isOver24Hour() {
		return true;
	}
	
	@Autowired
	private UserService userService;
}

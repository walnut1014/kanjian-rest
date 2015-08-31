package name.walnut.controller.passport;

import javax.servlet.http.HttpSession;

import name.walnut.auth.dto.OnlineUser;
import name.walnut.auth.entity.AuthAccount;
import name.walnut.auth.entity.User;
import name.walnut.auth.service.PassportService;
import name.walnut.common.BusinessException;
import name.walnut.controller.Const;
import name.walnut.controller.passport.vo.LoginParam;
import name.walnut.controller.utils.OnlineUtils;
import name.walnut.controller.utils.UploadUtils;
import name.walnut.utils.StringUtils;
import name.walnut.web.vo.Normal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/passport")
public class RegisterController {

	@RequestMapping(value = "register", headers="content-type=multipart/form-data", method = RequestMethod.POST)
	public OnlineUser register(HttpSession session, @RequestParam("photo") MultipartFile myFile, 
												@RequestParam("nickName") String nickName,
												@RequestParam("password") String password,
												@RequestParam("token") String token,
												@RequestParam("deviceToken") String deviceToken) {

		User user = new User();
		if(!myFile.isEmpty()){
			user.setHeadPhotoPath(UploadUtils.upload(myFile, Const.HEAD_PHOTO_GROUP));
		}
		return regist_(session, nickName, password, token, deviceToken, user);
	}
	
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public OnlineUser register(HttpSession session, @RequestParam("nickName") String nickName,
												@RequestParam("password") String password,
												@RequestParam("token") String token,
												@RequestParam("deviceToken") String deviceToken) {
		User user = new User();
		user.setNickName(nickName);
		return regist_(session, nickName, password, token, deviceToken, user);
	}

	

	@RequestMapping(value = "register/sendCode", method = RequestMethod.GET)
	public Normal sendCode(@RequestParam("mobilephone") String mobilephone, HttpSession session) {
		
		if(passportService.isExist(mobilephone))
			throw new BusinessException("此用户已存在");
		
		session.setAttribute(Const.MOBILEPHONE, mobilephone);
		return Normal.INSTANCE;
	}
	
	private OnlineUser regist_(HttpSession session, String nickName,
						String password, String token, String deviceToken, User user) {
		if(!token.equals(session.getAttribute(Const.MESSAGE_TOKEN)))
			throw new BusinessException("注册时非法调用");
		if (StringUtils.isEmpty(nickName) || 
				StringUtils.isEmpty(password)) 
			throw new BusinessException("昵称与密码不能为空");
		
		AuthAccount authAccount = new AuthAccount();
		authAccount.setPassword(password);
		authAccount.setMobilephone(session.getAttribute(Const.MOBILEPHONE).toString());
		
		passportService.register(authAccount, user);
		
		LoginParam loginParam = new LoginParam();
		loginParam.setDeviceToken(deviceToken);
		loginParam.setMobilephone(authAccount.getMobilephone());
		loginParam.setPassword(authAccount.getPassword());
		//返回用户头像路径
		return OnlineUtils.login(loginParam);
	}

	
	@Autowired
	private PassportService passportService;
	
}

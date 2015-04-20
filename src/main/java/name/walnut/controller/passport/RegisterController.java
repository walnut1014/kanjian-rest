package name.walnut.controller.passport;

import javax.servlet.http.HttpSession;

import name.walnut.auth.entity.AuthAccount;
import name.walnut.auth.entity.User;
import name.walnut.auth.service.PassportService;
import name.walnut.common.BusinessException;
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

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public Normal register(HttpSession session, @RequestParam("photo") MultipartFile myFile, 
												@RequestParam("nickName") String nickName,
												@RequestParam("password") String password,
												@RequestParam("token") String token) {

		if(!token.equals(session.getAttribute(Const.MESSAGE_TOKEN)))
			throw new BusinessException("注册时非法调用");
		if (StringUtils.isEmpty(nickName) || 
				StringUtils.isEmpty(password)) 
			throw new BusinessException("昵称与密码不能为空");
		
		AuthAccount authAccount = new AuthAccount();
		authAccount.setPassword(password);
		authAccount.setMobilephone(session.getAttribute(Const.MOBILEPHONE).toString());
		
		User user = new User();
		if(!myFile.isEmpty()){
			user.setHeadPhotoPath(authAccount.getMobilephone() + "_head");
			UploadUtils.upload(myFile, user.getHeadPhotoPath());
		}
		user.setNickName(nickName);
		
		passportService.register(authAccount, user);
		
		session.setAttribute(Const.CURRENT_AUTH, authAccount);
		return Normal.INSTANCE;
	}
	

	@RequestMapping(value = "register/sendCode", method = RequestMethod.GET)
	public Normal sendCode(@RequestParam("mobilephone") String mobilephone, HttpSession session) {
		
		if(passportService.isExist(mobilephone))
			throw new BusinessException("此用户已存在");
		
		session.setAttribute(Const.MOBILEPHONE, mobilephone);
		return Normal.INSTANCE;
	}

	
	@Autowired
	private PassportService passportService;
	
}

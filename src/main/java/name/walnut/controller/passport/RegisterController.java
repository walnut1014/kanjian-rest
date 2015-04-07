package name.walnut.controller.passport;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import name.walnut.web.vo.Normal;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/passport")
public class RegisterController {

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public Normal register(HttpSession session, @RequestParam("photo") MultipartFile myFile) {

		try {
			FileUtils.copyInputStreamToFile(myFile.getInputStream(), new File(
					"D:/ff", myFile.getOriginalFilename()));
		} catch (IOException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * if(!session.getAttribute(MESSAGE_TOKEN).equals(registerVo.getToken()))
		 * throw new BusinessException("注册时非法调用");
		 * 
		 * if (StringUtils.isEmpty(registerVo.getNickName()) ||
		 * StringUtils.isEmpty(registerVo.getPassword())) throw new
		 * BusinessException("昵称与密码不能为空");
		 * 
		 * AuthAccount authAccount = new AuthAccount();
		 * authAccount.setPassword(registerVo.getPassword());
		 * authAccount.setNickName(registerVo.getNickName());
		 * authAccount.setMobilephone
		 * (session.getAttribute(MOBILEPHONE).toString());
		 * 
		 * authAccountService.register(authAccount);
		 */
		return Normal.INSTANCE;
	}

}

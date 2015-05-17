package name.walnut.controller.common;

import java.util.Map;

import javax.servlet.http.HttpSession;

import name.walnut.auth.service.UserService;
import name.walnut.common.BusinessException;
import name.walnut.controller.Const;
import name.walnut.controller.utils.OnlineUtils;
import name.walnut.controller.utils.UploadUtils;
import name.walnut.web.vo.Normal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 设置控制器
 * @author walnut
 *
 */
@RestController
@RequestMapping("/setting")
public class SettingController {
	
	@RequestMapping(value="modifyHeadPhoto", method=RequestMethod.POST)
	public String modifyHeedPohoto(@RequestParam("photo") MultipartFile myFile) {
		
		if(myFile.isEmpty())
			throw new BusinessException("文件为空");

		String path = UploadUtils.upload(myFile, Const.HEAD_PHOTO_GROUP);
		userService.modifyHeadPhoto(path);
		return path;
	}
	
	@RequestMapping(value="modifyNickName", method=RequestMethod.POST)
	public Normal modifyNickName(@RequestBody Map<String, String> param) {
		
		userService.modifyNickName(param.get("nickName"));
		return Normal.INSTANCE;
	}
	
	@RequestMapping(value = "isCurrMobilephone", method = RequestMethod.GET)
	public Normal isCurrMobilephone(@RequestParam("mobilephone") String mobilephone) {
		
		if(!OnlineUtils.getOnlineAuth().getMobilephone().equals(mobilephone))
			throw new BusinessException("手机号不是当前登录用户手机号");
		
		return Normal.INSTANCE;
	}
	
	@RequestMapping(value="exit", method=RequestMethod.POST)
	public void exit(HttpSession session) {
		session.invalidate();
	}
	
	
	@Autowired
	private UserService userService;
}

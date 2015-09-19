package name.walnut.auth.service.impl;

import name.walnut.auth.dao.UserDao;
import name.walnut.auth.dto.OnlineUser;
import name.walnut.common.entity.User;
import name.walnut.auth.service.PassportService;
import name.walnut.common.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PassportServiceImpl implements PassportService {
	
	@Override
	public OnlineUser login(String mobilephone, String password) {
		
		if(!isExist(mobilephone))
			throw new BusinessException("您的手机号"+mobilephone+"还未注册", -1);
		
		OnlineUser onlineUser = userDao.getOnlineUser(mobilephone, password);
		if(onlineUser == null)
			throw new BusinessException("登陆密码错误", -2);
				
		return onlineUser;
	}
	
	@Override
	public void register(User user) {
		
		if(isExist(user.getPhone()))
			throw new BusinessException("此用户已注册");
		
		userDao.save(user);
	}
	
	@Override
	public boolean isExist(String mobilephone) {
		
		return userDao.getUserByMobilephone(mobilephone) != null;	
	}
	
	@Override
	public void setPassword(String mobilephone, String newPassword) {
		
		userDao.getUserByMobilephone(mobilephone).setPassword(newPassword);
	}
	
	@Autowired
	private UserDao userDao;

}

package name.walnut.auth.service.impl;

import name.walnut.auth.dao.AuthAccountDao;
import name.walnut.auth.dao.UserDao;
import name.walnut.auth.entity.AuthAccount;
import name.walnut.auth.entity.User;
import name.walnut.auth.service.PassportService;
import name.walnut.common.BusinessException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassportServiceImpl implements PassportService {
	
	@Override
	public AuthAccount login(String mobilephone, String password) {
		AuthAccount authAccount = authAccountDao.getMapper().getAuthAccountByMobilephone(mobilephone);
		if(authAccount == null)
			throw new BusinessException("您的手机号"+mobilephone+"还未注册", -1);
		if(!password.equals(authAccount.getPassword()))
			throw new BusinessException("登陆密码错误", -2);
		
		return authAccount;
	}
	
	@Override
	public void register(AuthAccount authAccount, User user) {
		
		if(isExist(authAccount.getMobilephone()))
			throw new BusinessException("此用户已注册");
		
		long userId = authAccountDao.insert(authAccount);
		user.setId(userId);
		userDao.getMapper().insert(user);
	}
	
	@Override
	public boolean isExist(String mobilephone) {
		
		return authAccountDao.getMapper().getCountByMobilephone(mobilephone) > 0;	
	}
	
	@Override
	public void setPassword(String mobilephone, String newPassword) {
		
		authAccountDao.getMapper().updatePassowrdByMobilephone(mobilephone, newPassword);
	}
	
	
	@Autowired
	private AuthAccountDao authAccountDao;
	
	@Autowired
	private UserDao userDao;


}

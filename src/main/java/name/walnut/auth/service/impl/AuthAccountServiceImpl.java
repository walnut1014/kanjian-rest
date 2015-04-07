package name.walnut.auth.service.impl;

import name.walnut.auth.dao.AuthAccountDao;
import name.walnut.auth.entity.AuthAccount;
import name.walnut.auth.service.AuthAccountService;
import name.walnut.common.BusinessException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthAccountServiceImpl implements AuthAccountService {
	
	@Override
	public void login(String mobilephone, String password) {
		String _password = authAccountDao.getMapper().getPasswordByMobilephone(mobilephone);
		if(StringUtils.isEmpty(_password))
			throw new BusinessException("您的手机号"+mobilephone+"还未注册", -1);
		if(!password.equals(_password))
			throw new BusinessException("登陆密码错误", -2);
	}
	
	@Override
	public void register(AuthAccount authAccount) {
		
		isExist(authAccount.getMobilephone());
		
		authAccountDao.insert(authAccount);
	}
	
	@Override
	public void isExist(String mobilephone) {
		
		if(authAccountDao.getMapper().getCountByMobilephone(mobilephone) > 0)
			throw new BusinessException("此用户已存在");
	}
	
	
	@Autowired
	private AuthAccountDao authAccountDao;

}

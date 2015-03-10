package name.walnut.auth.service.impl;

import name.walnut.auth.dao.AuthAccountDao;
import name.walnut.auth.entity.AuthAccount;
import name.walnut.auth.service.AuthAccountService;
import name.walnut.common.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthAccountServiceImpl implements AuthAccountService {
	
	@Override
	public void login() {

	}
	
	@Override
	public void register(String mobilephone) {
		
		if(authAccountDao.getMapper().getCountByMobilephone(mobilephone) > 0)
			throw new ServiceException("此用户已存在");
			
		AuthAccount authAccount = new AuthAccount(mobilephone);
		authAccountDao.insert(authAccount);
	}
	
	@Autowired
	private AuthAccountDao authAccountDao;
}

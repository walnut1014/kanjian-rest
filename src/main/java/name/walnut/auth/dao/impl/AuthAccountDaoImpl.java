package name.walnut.auth.dao.impl;

import java.util.Date;

import name.walnut.auth.dao.AuthAccountDao;
import name.walnut.auth.entity.AuthAccount;
import name.walnut.mapper.auth.AuthAccountMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AuthAccountDaoImpl implements AuthAccountDao {

	@Override
	public long insert(AuthAccount authAccount) {

		authAccount.setCreateDate(new Date());
		authAccountMapper.insert(authAccount);
		return authAccount.getId();
	}
	
	@Override
	public AuthAccountMapper getMapper() {
		return authAccountMapper;
	}
	
	@Autowired
	private AuthAccountMapper authAccountMapper;
	
	
}

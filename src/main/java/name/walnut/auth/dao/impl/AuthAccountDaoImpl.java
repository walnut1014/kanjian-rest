package name.walnut.auth.dao.impl;

import java.util.Date;
import java.util.Random;

import name.walnut.auth.dao.AuthAccountDao;
import name.walnut.auth.entity.AuthAccount;
import name.walnut.mapper.auth.AuthAccountMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AuthAccountDaoImpl implements AuthAccountDao {

	@Override
	public long insert(AuthAccount authAccount) {

		long id = new Random().nextInt(10000);
		authAccount.setId(id);
		authAccount.setCreateDate(new Date());
		authAccountMapper.insert(authAccount);
		return id;
	}
	
	@Override
	public AuthAccountMapper getMapper() {
		return authAccountMapper;
	}
	
	@Autowired
	private AuthAccountMapper authAccountMapper;
}

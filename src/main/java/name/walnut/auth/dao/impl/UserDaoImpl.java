package name.walnut.auth.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import name.walnut.auth.dao.UserDao;
import name.walnut.mapper.auth.UserMapper;

@Repository
public class UserDaoImpl implements UserDao {

	
	
	@Override
	public UserMapper getMapper() {
		
		return userMapper;
	}
	
	@Autowired
	private UserMapper userMapper;

}

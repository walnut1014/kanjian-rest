package name.walnut.auth.dao.impl;


import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import name.walnut.auth.dao.UserDao;
import name.walnut.auth.service.UserService;
import name.walnut.test.TestSupport;

public class UserDaoImplTest extends TestSupport {

	@Test
	public void testFindUserByIds() {
		userDao.get(20);
	}
	

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SessionFactory sessionFactory;
}

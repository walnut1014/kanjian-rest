package name.walnut.auth.dao.impl;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import name.walnut.auth.dao.UserDao;
import name.walnut.auth.dto.UserWithMobile;
import name.walnut.auth.entity.User;
import name.walnut.test.TestSupport;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDaoImplTest extends TestSupport {

	@Test
	public void testFindUserByIds() {
		Set<Long> ids = new HashSet<>();
		ids.add(20L);
		ids.add(21L);
		Map<Long, UserWithMobile> map = userDao.findUserByIds(ids);
		System.out.println(map);
	}
	

	@Autowired
	private UserDao userDao;
}

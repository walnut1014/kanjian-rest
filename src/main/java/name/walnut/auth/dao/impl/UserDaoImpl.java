package name.walnut.auth.dao.impl;

import name.walnut.auth.dao.UserDao;
import name.walnut.auth.dto.OnlineUser;
import name.walnut.auth.dto.UserWithMobile;
import name.walnut.common.entity.User;
import name.walnut.common.HibernateGenerationDao;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Repository
//TODO 待改造
public class UserDaoImpl extends HibernateGenerationDao<User> implements UserDao {

	@Override
	public Map<Long, UserWithMobile> findUserByIds(Set<Long> ids) {
		
		List<UserWithMobile> users = null/*= userMapper.findUserByIds(StringUtils.getInString(ids))*/;
		Map<Long, UserWithMobile> result = new HashMap<>();
		for(UserWithMobile um : users) {
			result.put(um.getId(), um);
		}
		return result; 
	}
	
	@Override
	public List<UserWithMobile> findUserByMobilephones(String[] mobilephones) {
		Set<String> mobileSet = new HashSet<>();
		for(String s : mobilephones)
			mobileSet.add(s);
		
		if(CollectionUtils.isEmpty(mobileSet))
			return new ArrayList<>();
		//return userMapper.findUserByMobilephones(StringUtils.getInString(mobileSet));
		return null;
	}

	@Override
	public OnlineUser getOnlineUser(String mobilephone, String password) {
		
		return (OnlineUser) getSession().createQuery("FROM OnlineUser WHERE mobilephone=:mobilephone AND password=:password")
				.setString("mobilephone", mobilephone)
				.setString("password", password)
				.uniqueResult();
	}

	@Override
	public User getUserByMobilephone(String mobilephone) {
		return (User) getSession().createQuery("from User where mobilephone=:mobilephone")
						   .setString("mobilephone", mobilephone).uniqueResult();
	}

	
}

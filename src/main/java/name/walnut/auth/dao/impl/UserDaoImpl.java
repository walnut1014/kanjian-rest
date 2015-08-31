package name.walnut.auth.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import name.walnut.auth.dao.UserDao;
import name.walnut.auth.dto.UserWithMobile;
import name.walnut.auth.entity.User;
import name.walnut.mapper.auth.UserMapper;
import name.walnut.utils.StringUtils;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

@Repository
public class UserDaoImpl implements UserDao {

	@Override
	public Map<Long, UserWithMobile> findUserByIds(Set<Long> ids) {
		
		List<UserWithMobile> users = userMapper.findUserByIds(StringUtils.getInString(ids));
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
		return userMapper.findUserByMobilephones(StringUtils.getInString(mobileSet));
	}
	
	@Override
	public User get(long id) {
		return userMapper.get(id);
	}
	
	
	@Override
	public UserMapper getMapper() {
		
		return userMapper;
	}
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private SqlSession sqlSession;

}

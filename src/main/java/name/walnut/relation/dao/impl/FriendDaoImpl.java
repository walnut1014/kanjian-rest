package name.walnut.relation.dao.impl;

import name.walnut.mapper.relation.FriendMapper;
import name.walnut.relation.dao.FriendDao;
import name.walnut.common.entity.Friend;
import name.walnut.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class FriendDaoImpl implements FriendDao {

	@Override
	public FriendMapper getMapper() {
		return friendMapper;
	}

	@Override
	public void insert(Friend friend) {
		friend.setCreateDate(DateUtils.getNow());
		friendMapper.insert(friend);
	}
	
	@Override
	public Set<Long> getFriendIds(long userId) {
		return friendMapper.getFriendIds(userId);
	}
	
	@Autowired
	private FriendMapper friendMapper;


}

package name.walnut.relation.dao;

import name.walnut.common.DeprecatedBaseDao;
import name.walnut.mapper.relation.FriendMapper;
import name.walnut.common.entity.Friend;

import java.util.Set;

public interface FriendDao extends DeprecatedBaseDao<FriendMapper> {
	
	void insert(Friend friend);
	
	/**
	 * 获得一个用户的所有好友的ID
	 * @param userId
	 * @return
	 */
	Set<Long> getFriendIds(long userId);
}

package name.walnut.relation.dao.impl;

import name.walnut.common.HibernateGenerationDao;
import name.walnut.mapper.relation.FriendMapper;
import name.walnut.relation.dao.FriendDao;
import name.walnut.common.entity.Friend;
import name.walnut.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class FriendDaoImpl extends HibernateGenerationDao<Friend> implements FriendDao {
	
	@Override
	public Set<Long> getFriendIds(long userId) {
		return null;
	}



}

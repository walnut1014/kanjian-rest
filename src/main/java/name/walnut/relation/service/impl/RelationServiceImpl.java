package name.walnut.relation.service.impl;

import name.walnut.auth.dao.UserDao;
import name.walnut.auth.dto.UserWithMobile;
import name.walnut.common.BusinessException;
import name.walnut.controller.utils.OnlineUtils;
import name.walnut.relation.dao.FriendDao;
import name.walnut.relation.dao.RelationDao;
import name.walnut.relation.dto.FriendAndPhotoCount;
import name.walnut.relation.dto.MobileRelation;
import name.walnut.common.entity.Friend;
import name.walnut.relation.entity.Relation;
import name.walnut.relation.service.RelationService;
import name.walnut.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class RelationServiceImpl implements RelationService {

	@Override
	public int getFriendsCount() {
		return  0; //friendDao.getMapper().getFriendCount(OnlineUtils.getOnlineUserId());
	}
	
	@Override
	public int getUnreadCount() {
		return relationDao.getMapper().getUnreadCount(OnlineUtils.getOnlineUserId());
	}
	
	@Override
	public List<MobileRelation> getAllRelation() {
		
		List<Relation> relations = relationDao.getMapper()
										.getRelations(OnlineUtils.getOnlineUserId());	
		Set<Long> ids = new HashSet<>();
		List<MobileRelation> result = new ArrayList<>();
		
		Map<Long, UserWithMobile> inviteMap = getInviteMap(relations);
		Map<Long, UserWithMobile> otherMap = getOtherMap(relations);
		
		for(Relation relation : relations) {
			if(relation.getUserId() == OnlineUtils.getOnlineUserId())
				result.add(new MobileRelation(inviteMap.get(relation.getRelationId()), 
								   relation.isAgree(), true));
			else
				result.add(new MobileRelation(otherMap.get(relation.getUserId()), 
						   relation.isAgree(), false));
			ids.add(relation.getId());
		}
		if(!CollectionUtils.isEmpty(ids))
			relationDao.getMapper().updateRead(StringUtils.getInString(ids));
		return result;
	}
	
	@Override
	public void invit(long id) {
		
		if(OnlineUtils.getOnlineUserId() == id)
			throw new BusinessException("不能邀请自己为好友");
		
		Relation relation = new Relation();
		relation.setUserId(OnlineUtils.getOnlineUserId());
		relation.setRelationId(id);
		relation.setAgree(false);
		relationDao.insert(relation);
	}
	
	@Override
	@Deprecated
	public void agreeInvit(long id) {
		long InvitedId = OnlineUtils.getOnlineUserId(); //被邀请人
		
		Relation query = new Relation();
		query.setUserId(id);
		query.setRelationId(InvitedId);
		query.setAgree(false);
		Long relationId = relationDao.getMapper().findId(query);
		
		if(relationId == null)
			throw new BusinessException("非法操作");
		
		relationDao.getMapper().updateAgree(relationId, true);

		//friendDao.insert(null);
	}
	
	@Override
	public List<FriendAndPhotoCount> getAllFindWhithPhotoCount() {
		
		return null; // friendDao.getMapper().getFriends(OnlineUtils.getOnlineUserId());
	}
	
	/**
	 * 获得邀请的人
	 * @return 
	 */
	public Map<Long, UserWithMobile> getInviteMap(List<Relation> relations) {
		
		Set<Long> inviteSet = new HashSet<>();
		
		for(Relation relation : relations) {
			if(relation.getUserId() == OnlineUtils.getOnlineUserId())
				inviteSet.add(relation.getRelationId());
		}
		if(CollectionUtils.isEmpty(inviteSet))
			return new HashMap<Long, UserWithMobile>();
		return userDao.findUserByIds(inviteSet);
	}
	
	/**
	 * 获得被邀请的人
	 * @return 
	 */
	public Map<Long, UserWithMobile> getOtherMap(List<Relation> relations) {
		
		Set<Long> set = new HashSet<>();
		
		for(Relation relation : relations) {
			if(relation.getUserId() != OnlineUtils.getOnlineUserId())
				set.add(relation.getUserId());
		}
		
		if(CollectionUtils.isEmpty(set))
			return new HashMap<Long, UserWithMobile>();
		return userDao.findUserByIds(set);
	}
	

	@Autowired
	private RelationDao relationDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private FriendDao friendDao;

}

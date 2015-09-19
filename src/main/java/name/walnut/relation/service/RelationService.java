package name.walnut.relation.service;

import name.walnut.auth.dto.UserWithMobile;
import name.walnut.relation.dto.FriendAndPhotoCount;
import name.walnut.relation.dto.MobileRelation;
import name.walnut.relation.entity.Relation;

import java.util.List;
import java.util.Map;


public interface RelationService {
	
	/**
	 * 获得好友个数
	 * @return
	 */
	int getFriendsCount();
	
	/**
	 * 获得未读消息个数
	 * @return
	 */
	int getUnreadCount();
	
	/**
	 * 获得所有的好友请求
	 * @return
	 */
	List<MobileRelation> getAllRelation();
	
	/**
	 * 向别人发出邀请
	 * @param id 被邀请人的id
	 * @deprecated 被InviteService.invite()替换
	 */
	@Deprecated
	void invit(long id);
	
	/**
	 * 邀请人ID
	 * @param id
	 */
	void agreeInvit(long id);
	
	/**
	 * 获得用户所有的朋友以及朋友的照片数
	 * @return
	 */
	List<FriendAndPhotoCount> getAllFindWhithPhotoCount();
	
	/**
	 * 获得当前用户作为邀请人的
	 * @param relations
	 * @return
	 */
	Map<Long, UserWithMobile> getInviteMap(List<Relation> relations);
	
	/**
	 * 获得当前用户作为被邀请人的
	 * @param relations
	 * @return
	 */
	Map<Long, UserWithMobile> getOtherMap(List<Relation> relations);
	
}

package name.walnut.auth.service.impl;

import name.walnut.auth.dao.UserDao;
import name.walnut.auth.dto.UserQueryResult;
import name.walnut.auth.dto.UserWithMobile;
import name.walnut.auth.service.UserService;
import name.walnut.common.BusinessException;
import name.walnut.controller.utils.OnlineUtils;
import name.walnut.controller.utils.UploadUtils;
import name.walnut.relation.dao.RelationDao;
import name.walnut.relation.entity.Relation;
import name.walnut.relation.service.RelationService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Override
	public List<UserQueryResult> findUserByMobile(String mobilephone) {

		//UserWithMobile um = userDao.findUserByMobilephone(mobilephone);
		UserWithMobile um  = null;
		if(um == null)
			return new ArrayList<>();
		Relation query = new Relation();
		query.setUserId(OnlineUtils.getOnlineUserId());
		query.setRelationId(um.getId());
		Relation relation = relationDao.getMapper().findRelation(query);
		
		List<UserQueryResult> result = new ArrayList<>();
		
		if(relation!=null){
			result.add(new UserQueryResult(um, true, relation.isAgree()));
		}else {
			query.setUserId(um.getId());
			query.setRelationId(OnlineUtils.getOnlineUserId());
			relation = relationDao.getMapper().findRelation(query);
			if(relation != null)
				result.add(new UserQueryResult(um, false, relation.isAgree()));
			else
				result.add(new UserQueryResult(um));
		}
		return result;
	}
	
	@Override
	public List<UserQueryResult> findUserByMobiles(String[] mobilephones) {
		
		final long onlineUserId = OnlineUtils.getOnlineUserId();
		List<UserWithMobile> ums = userDao.findUserByMobilephones(mobilephones);
		
		List<Relation> relations = relationDao.getMapper().getRelations(onlineUserId);
		
		Map<Long, UserWithMobile> invitMap = relationService.getInviteMap(relations);
		Map<Long, UserWithMobile> otherMap = relationService.getOtherMap(relations);
		
		List<UserQueryResult> result = new ArrayList<>();
		
		for(UserWithMobile um : ums) {
			if(invitMap.containsKey(um.getId())){
				result.add(new UserQueryResult(um, true, 
							isAgree(relations, onlineUserId, um.getId())));
			}
			else if(otherMap.containsKey(um.getId())){
				result.add(new UserQueryResult(um, false, 
						isAgree(relations, um.getId(), onlineUserId)));
			}
			else {
				result.add(new UserQueryResult(um));
			}
		}
		
		return result;
	}
	
	/**
	 * 更新用户昵称
	 */
	@Override
	public void modifyNickName(String nickName) {
//		userDao.getMapper().updateNickName(OnlineUtils.getOnlineUserId(), nickName);
	}
	
	@Override
	public void modifyHeadPhoto(String path) {
		
		File oldFile = new File(UploadUtils.getUploadPath()
								+OnlineUtils.getOnlineAuth().getHeadPhotoPath());
		if(oldFile.exists())
			FileUtils.deleteQuietly(oldFile);
		
//		userDao.getMapper().updateHeadPhoto(OnlineUtils.getOnlineUserId(), path);
	}
	
	/**
	 * 查询是否同意
	 * @param relations
	 * @return
	 */
	private boolean isAgree(List<Relation> relations, long userId, long relationId) {
		
		for(Relation relation : relations) {
			if(relation.getUserId() == userId 
					&& relation.getRelationId() == relationId)
				return relation.isAgree();
		}
		throw new BusinessException("系统错误");
	}
	
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RelationDao relationDao;
	
	@Autowired
	private RelationService relationService;

}

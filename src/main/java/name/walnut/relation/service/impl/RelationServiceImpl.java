package name.walnut.relation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import name.walnut.controller.relation.vo.RelationCount;
import name.walnut.relation.dao.RelationDao;
import name.walnut.relation.service.RelationService;

public class RelationServiceImpl implements RelationService {

	@Override
	public RelationCount getRelationCount(String mobilephone) {
		
		//relationDao.getMapper().getFriendCount(userId)
		return null;
	}

	@Autowired
	private RelationDao relationDao;
}

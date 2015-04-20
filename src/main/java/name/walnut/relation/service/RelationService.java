package name.walnut.relation.service;

import name.walnut.controller.relation.vo.RelationCount;

public interface RelationService {
	
	/**
	 * 获得被邀请个数和好友数
	 * @param mobilephone
	 * @return
	 */
	RelationCount getRelationCount(String mobilephone);
}

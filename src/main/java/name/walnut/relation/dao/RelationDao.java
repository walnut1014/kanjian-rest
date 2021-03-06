package name.walnut.relation.dao;

import name.walnut.common.BaseDao;
import name.walnut.mapper.relation.RelationMapper;
import name.walnut.relation.entity.Relation;

public interface RelationDao extends BaseDao<RelationMapper> {
	
	void insert(Relation relation);
}

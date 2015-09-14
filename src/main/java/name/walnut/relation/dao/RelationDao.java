package name.walnut.relation.dao;

import name.walnut.common.DeprecatedBaseDao;
import name.walnut.mapper.relation.RelationMapper;
import name.walnut.relation.entity.Relation;

public interface RelationDao extends DeprecatedBaseDao<RelationMapper> {
	
	void insert(Relation relation);
}

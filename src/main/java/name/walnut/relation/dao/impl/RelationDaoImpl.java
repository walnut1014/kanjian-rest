package name.walnut.relation.dao.impl;

import name.walnut.mapper.relation.RelationMapper;
import name.walnut.relation.dao.RelationDao;
import name.walnut.relation.entity.Relation;
import name.walnut.utils.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RelationDaoImpl implements RelationDao {

	@Override
	public RelationMapper getMapper() {
		return relationMapper;
	}
	
	@Override
	public void insert(Relation relation) {
		relation.setCreateDate(DateUtils.getNow());
		relationMapper.insert(relation);
	}
	
	@Autowired
	private RelationMapper relationMapper;

}

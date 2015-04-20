package name.walnut.relation.dao.impl;

import name.walnut.relation.RelationStatus;
import name.walnut.relation.dao.RelationDao;
import name.walnut.relation.entity.Relation;
import name.walnut.test.TestSupport;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RelationDaoImplTest extends TestSupport {

	//@Test
	public void testInsert() {
		Relation relation = new Relation();
		relation.setUserId(1L);
		relation.setRelationId(3L);
		relation.setRelationStatus(RelationStatus.waitVerify);
		relationDao.insert(relation);
	}
	
	
	
	@Autowired
	private RelationDao relationDao;

}

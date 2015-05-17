package name.walnut.relation.dao.impl;

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
		relation.setAgree(false);
		relationDao.insert(relation);
	}
	
	@Test
	public void testFindId() {
		Relation relation = new Relation();
		relation.setUserId(21);
		relation.setRelationId(1);
		relation.setAgree(false);
		
		System.out.println(relationDao.getMapper().findId(relation));
	}
	
	@Test
	public void testUpdateStatus() {
		
		relationDao.getMapper().updateAgree(1, true);
	}
	
	@Test
	public void testFindRelation() {
		
		Relation relation = new Relation();
		relation.setUserId(21);
		relation.setRelationId(20);
		relationDao.getMapper().findRelation(relation);
	}

	@Autowired
	private RelationDao relationDao;

}

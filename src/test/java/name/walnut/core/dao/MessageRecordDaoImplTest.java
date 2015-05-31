package name.walnut.core.dao;

import name.walnut.core.entity.MessageRecord;
import name.walnut.test.TestSupport;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MessageRecordDaoImplTest extends TestSupport {

	@Test
	public void testInsert() {
		MessageRecord messageRecord = new MessageRecord();
		messageRecord.setContent("asdf");
		messageRecord.setId(3L);
		messageRecordDao.insert(messageRecord);
	}
	
	@Autowired
	private MessageRecordDao messageRecordDao;

}

package name.walnut.core.dao.impl;

import name.walnut.core.dao.MessageRecordDao;
import name.walnut.core.entity.MessageRecord;
import name.walnut.mapper.core.MessageRecordMapper;
import name.walnut.utils.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MessageRecordDaoImpl implements MessageRecordDao {

	
	@Override
	public void insert(MessageRecord messageRecord) {
		messageRecord.setCreateDate(DateUtils.getNow());
		messageRecordMapper.insert(messageRecord);
	}
	
	@Override
	public void updateNode(long id, long left) {
		
	}
	
	@Override
	public MessageRecordMapper getMapper() {
		return messageRecordMapper;
	}
	
	@Autowired
	private MessageRecordMapper messageRecordMapper;
}

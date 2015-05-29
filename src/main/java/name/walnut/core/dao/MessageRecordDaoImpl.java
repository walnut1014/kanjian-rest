package name.walnut.core.dao;

import name.walnut.mapper.core.MessageRecordMapper;

import org.springframework.beans.factory.annotation.Autowired;

public class MessageRecordDaoImpl implements MessageRecordDao {

	@Override
	public MessageRecordMapper getMapper() {
		return messageRecordMapper;
	}
	
	@Override
	public void updateNode(long id, long left) {
		// TODO Auto-generated method stub
		
	}
	
	@Autowired
	private MessageRecordMapper messageRecordMapper;

}

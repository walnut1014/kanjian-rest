package name.walnut.core.dao.impl;

import name.walnut.common.entity.User;
import name.walnut.common.Page;
import name.walnut.core.dao.MessageRecordDao;
import name.walnut.core.entity.MessageRecord;
import name.walnut.mapper.core.MessageRecordMapper;
import name.walnut.utils.DateUtils;
import name.walnut.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MessageRecordDaoImpl implements MessageRecordDao {

	
	@Override
	public void insert(MessageRecord messageRecord) {
		messageRecord.setCreateDate(DateUtils.getNow());
		messageRecordMapper.insert(messageRecord);
	}
	
	@Override
	public MessageRecord getMainMessageRecordById(long id) {
		return messageRecordMapper.get(id);
	}
	

	
	@Override
	public MessageRecordMapper getMapper() {
		return messageRecordMapper;
	}
	
	@Override
	public List<MessageRecord> findMainMessageRecordByIds(Set<Long> ids, Page page) {
		
		int start = (page.getPageNo() -1) * page.getPageSize();
		return messageRecordMapper.findMainMessageRecordByIds(StringUtils.getInString(ids), 
															start,  page.getPageSize());
	}
	
	@Override
	public Map<Long, List<MessageRecord>> findRepayMessageRecordByIds(Set<Long> ids) {
		
		List<MessageRecord> result = messageRecordMapper
										.findRepayMessageRecordByIds(StringUtils.getInString(ids));
		Map<Long, List<MessageRecord>> map = new HashMap<>();
		for(MessageRecord record : result) {
			if(!map.containsKey(record.getBatchId()))
				map.put(record.getBatchId(), new ArrayList<MessageRecord>());
			map.get(record.getBatchId()).add(record);
		}
		return map;
	}
	
	@Override
	public Map<Long, String> findNichNameByBatchId(Set<Long> ids) {
		Map<Long,String> result = new HashMap<>();
		for(User user : messageRecordMapper.findNichNameByBatchId(StringUtils.getInString(ids)))
			result.put(user.getId(), user.getNickName());
		return result;
	}
	
	@Override
	public List<Long> findBatchFriendId(long batchId, long userId) {
		return messageRecordMapper.findBatchFriendId(batchId, userId);
	}
	
	@Autowired
	private MessageRecordMapper messageRecordMapper;

}

package name.walnut.core.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import name.walnut.common.DeprecatedBaseDao;
import name.walnut.common.Page;
import name.walnut.core.entity.MessageRecord;
import name.walnut.mapper.core.MessageRecordMapper;

public interface MessageRecordDao extends DeprecatedBaseDao<MessageRecordMapper> {
	
	
	/**
	 * 插入新的消息记录
	 * @param messageRecord
	 */
	void insert(MessageRecord messageRecord);
	
	/**
	 * 获得主消息记录通过用户ID集合
	 * @param ids
	 * @return
	 */
	List<MessageRecord> findMainMessageRecordByIds(Set<Long> ids, Page page);
	
	MessageRecord getMainMessageRecordById(long id);
	
	/**
	 * 通过主消息ID获得回复消息记录
	 * @param ids
	 * @return
	 */
	Map<Long, List<MessageRecord>> findRepayMessageRecordByIds(Set<Long> ids);
	
	/**
	 * 获得消息组中所需要的姓名
	 * @param batchId
	 * @return
	 */
	Map<Long, String> findNichNameByBatchId(Set<Long> ids);
	
	/**
	 * 获得一个batch的消息朋友ID
	 * @param batchId
	 * @return
	 */
	List<Long> findBatchFriendId(long batchId, long userId);
}

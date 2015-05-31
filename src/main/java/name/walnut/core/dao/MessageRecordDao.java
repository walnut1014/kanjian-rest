package name.walnut.core.dao;

import name.walnut.common.BaseDao;
import name.walnut.core.entity.MessageRecord;
import name.walnut.mapper.core.MessageRecordMapper;

public interface MessageRecordDao extends BaseDao<MessageRecordMapper> {
	
	/**
	 * 更新节点
	 * @param id
	 * @param left
	 */
	void updateNode(long id, long left);
	
	/**
	 * 插入新的消息记录
	 * @param messageRecord
	 */
	void insert(MessageRecord messageRecord);
}

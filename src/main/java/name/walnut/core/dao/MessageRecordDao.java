package name.walnut.core.dao;

import name.walnut.common.BaseDao;
import name.walnut.mapper.core.MessageRecordMapper;

public interface MessageRecordDao extends BaseDao<MessageRecordMapper> {
	
	/**
	 * 更新节点
	 * @param id
	 * @param left
	 */
	void updateNode(long id, long left);
}

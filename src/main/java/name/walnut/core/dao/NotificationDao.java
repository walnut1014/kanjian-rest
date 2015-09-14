package name.walnut.core.dao;

import java.util.List;

import name.walnut.common.DeprecatedBaseDao;
import name.walnut.core.pojo.Notification;
import name.walnut.mapper.core.NotificationMapper;

public interface NotificationDao extends DeprecatedBaseDao<NotificationMapper> {
	
	int getUnReadCount(long userId);
	
	List<Notification> getNewNotification(long userId);
	
	void deleteByBatchId(long messageId);
	
	void readNotification(long messageId, long userId);
}

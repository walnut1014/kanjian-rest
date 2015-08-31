package name.walnut.core.dao.impl;

import java.util.List;

import name.walnut.core.dao.NotificationDao;
import name.walnut.core.pojo.Notification;
import name.walnut.mapper.core.NotificationMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationDaoImpl implements NotificationDao {

	@Override
	public NotificationMapper getMapper() {
		return notificationMapper;
	}
	
	@Override
	public int getUnReadCount(long userId) {
		return notificationMapper.getUnReadCount(userId);
	}
	
	@Override
	public List<Notification> getNewNotification(long userId) {
		return notificationMapper.getNewNotification(userId);
	}
	
	@Override
	public void deleteByBatchId(long messageId) {
		notificationMapper.deleteByBatchId(messageId);
	}
	
	@Override
	public void readNotification(long messageId, long userId) {
		notificationMapper.updateRead(messageId, userId);
	}

	@Autowired
	private NotificationMapper notificationMapper;

}

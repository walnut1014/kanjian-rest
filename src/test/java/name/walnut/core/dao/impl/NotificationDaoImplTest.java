package name.walnut.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import name.walnut.core.dao.NotificationDao;
import name.walnut.core.pojo.Notification;
import name.walnut.core.pojo.NotificationType;
import name.walnut.test.TestSupport;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class NotificationDaoImplTest extends TestSupport{

	@Test
	public void test() {
		
		List<Notification> notifications = new ArrayList<>();
		Notification n = new Notification(182, 34);
		n.setNotificationType(NotificationType.main);
		notifications.add(n);
		notificationDao.getMapper().insert(notifications);
	}
	
	@Autowired
	private NotificationDao notificationDao;

}

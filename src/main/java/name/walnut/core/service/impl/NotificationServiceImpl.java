package name.walnut.core.service.impl;

import name.walnut.auth.dao.UserDao;
import name.walnut.common.entity.User;
import name.walnut.controller.utils.OnlineUtils;
import name.walnut.core.dao.MessageRecordDao;
import name.walnut.core.dao.NotificationDao;
import name.walnut.core.dto.NewMessage;
import name.walnut.core.entity.MessageRecord;
import name.walnut.core.pojo.Notification;
import name.walnut.core.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Override
	public int getCount() {
		return notificationDao.getUnReadCount(OnlineUtils.getOnlineUserId());
	}
	
	@Override
	public List<NewMessage> getNewMessage() {
		List<Notification> notifications = notificationDao.getNewNotification(
														OnlineUtils.getOnlineUserId());
		List<NewMessage> result = new ArrayList<>();
		for(Notification n : notifications) {
			User user = userDao.get(n.getUserId());
			MessageRecord messageRecord = messageRecordDao.getMapper().get(n.getMessageId());
			NewMessage newMessage = new NewMessage();
			newMessage.setMessageId(messageRecord.getBatchId());
			newMessage.setMessageSenderId(messageRecord.getSenderId());
			newMessage.setRepayer(user);
			if(messageRecord.isReply())
				messageRecord = messageRecordDao.getMapper().get(messageRecord.getBatchId());
			newMessage.setPhotoPath(messageRecord.getPhotoPath());
			newMessage.setRemark("测试");
			result.add(newMessage);
		}
		return result;
	}
	
	@Override
	public void readMessage(long messageId) {
		notificationDao.readNotification(messageId, OnlineUtils.getOnlineUserId());
	}

	
	@Autowired
	private NotificationDao notificationDao;
	
	@Autowired
	private MessageRecordDao messageRecordDao;
	
	@Autowired
	private UserDao userDao;
}

package name.walnut.core.impl;

import name.walnut.common.BusinessException;
import name.walnut.controller.utils.OnlineUtils;
import name.walnut.core.AbstractMessageSender;
import name.walnut.core.dao.MessageRecordDao;
import name.walnut.core.dao.NotificationDao;
import name.walnut.core.entity.MessageRecord;
import name.walnut.core.pojo.Notification;
import name.walnut.core.pojo.NotificationType;
import name.walnut.core.pojo.RepayMessage;
import name.walnut.relation.dao.FriendDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class RepayMessageSender extends AbstractMessageSender<RepayMessage> {

	@Override
	protected void validate(RepayMessage message) {
		if(message.getContent().length() > 200)
			throw new BusinessException("文字内容长度超过200");
	}

	@Override
	protected void customBuild(MessageRecord messageRecord, RepayMessage message) {
		
		messageRecord.setReply(true);
		messageRecord.setParentId(message.getPrentId());
		messageRecord.setBatchId(messageRecordDao.getMapper()
										.get(message.getPrentId()).getBatchId());
	}

	@Override
	protected void pushCustomNotification(RepayMessage repayMessage) {
		
		List<Long> friendIds = messageRecordDao.findBatchFriendId(
				messageRecordDao.getMainMessageRecordById(repayMessage.getId()).getBatchId(), OnlineUtils.getOnlineUserId());
		if(CollectionUtils.isEmpty(friendIds))
			return;
		List<Notification> notifications = new ArrayList<>();
		
		for(Long l : friendIds) {
			Notification notification = new Notification(repayMessage.getId(),l);
			notification.setNotificationType(NotificationType.repay);
			notifications.add(notification);
		}
		
		notificationDao.getMapper().insert(notifications);
	}
	
	@Autowired
	private MessageRecordDao messageRecordDao;
	
	@Autowired
	private FriendDao friendDao;
	
	@Autowired
	private NotificationDao notificationDao;

}

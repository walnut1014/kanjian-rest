package name.walnut.core.impl;

import name.walnut.auth.dao.UserDao;
import name.walnut.common.BusinessException;
import name.walnut.controller.utils.OnlineUtils;
import name.walnut.core.AbstractMessageSender;
import name.walnut.core.entity.MessageRecord;
import name.walnut.core.pojo.MainMessage;
import name.walnut.core.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainMessageSender extends AbstractMessageSender<MainMessage> {

	@Override
	protected void validate(MainMessage message) {
		if(message.getContent().length() > 200)
			throw new BusinessException("文字内容长度超过200");
	}

	@Override
	protected void customBuild(MessageRecord messageRecord, MainMessage message) {
		messageRecord.setBatchId(messageRecord.getId());
		messageRecord.setPhotoPath(message.getPhotoPath());
//		userDao.getMapper().updateLastSelectTime(OnlineUtils.getOnlineUserId(), null);
	}

	@Override
	protected void pushCustomNotification(MainMessage message) {

	}

	@Autowired
	private MessageService messageService;
	
	@Autowired
	private UserDao userDao;
}

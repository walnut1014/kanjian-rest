package name.walnut.core.impl;

import name.walnut.common.BusinessException;
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
		if(messageService.getLastSelectResidueTimeTo24() > 0)
			throw new BusinessException("距离上一次发送照片未超过24小时");
		if(message.getContent().length() > 200)
			throw new BusinessException("文字内容长度超过200");
	}

	@Override
	protected void customBuild(MessageRecord messageRecord, MainMessage message) {
		messageRecord.setPhotoPath(message.getPhotoPath());
	}

	@Override
	protected void pushCustomNotification(MainMessage message) {

	}

	@Autowired
	private MessageService messageService;
}

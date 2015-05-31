package name.walnut.core;

import name.walnut.controller.utils.DeviceTokenContainer;
import name.walnut.core.dao.MessageRecordDao;
import name.walnut.core.entity.MessageRecord;
import name.walnut.core.pojo.Message;
import name.walnut.push.PushHelp;
import name.walnut.relation.dao.FriendDao;
import name.walnut.sequence.SequenceEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractMessageSender<T extends Message> implements SendMessage {

	@Override
	@Transactional
	public void Send(Message message) {
		
		@SuppressWarnings("unchecked")
		final T t = (T)message;
		validate(t);
		
		MessageRecord messageRecord = buildMessageRecord(t);
		customBuild(messageRecord, t);
		messageRecordDao.insert(messageRecord);
		
		pushNotification(message.getSenderId());
		pushCustomNotification(t);
	}
	
	protected abstract void validate(T t);
	
	/**
	 * 发送通知
	 * @param senderId
	 */
	private void pushNotification(long senderId){
		
		PushHelp.INSTANCE.push(
				DeviceTokenContainer.INSTANCE.find(
							friendDao.getFriendIds(senderId))
							
				,"数据结构待定");
	}
	
	/**
	 * 构造一个MessageRecord
	 * @param message 消息
	 * @param startNo 开始节点
	 * @return
	 */
	private MessageRecord buildMessageRecord(Message message) {
		MessageRecord messageRecord = new MessageRecord();
		messageRecord.setSenderId(message.getSenderId());
		messageRecord.setId(SequenceEnum.MESSAGE_RECORD.next());
		messageRecord.setContent(message.getContent());
		
		return messageRecord;
	}
	
	protected abstract void customBuild(MessageRecord messageRecord, T message);
	
	/**
	 * 个性化通知推送
	 * @param t
	 */
	protected abstract void pushCustomNotification(T t);
	
	@Autowired
	private FriendDao friendDao;
	
	@Autowired
	private MessageRecordDao messageRecordDao;

}

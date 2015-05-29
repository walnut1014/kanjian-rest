package name.walnut.core;

import name.walnut.controller.utils.DeviceTokenContainer;
import name.walnut.core.dao.MessageRecordDao;
import name.walnut.core.entity.MessageRecord;
import name.walnut.core.pojo.Message;
import name.walnut.push.PushHelp;
import name.walnut.relation.dao.FriendDao;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractSendMessage<T extends Message> implements SendMessage {

	@Override
	public void Send(Message message) {
		
		@SuppressWarnings("unchecked")
		final T t = (T)message;
		validate(t);
		
		long startNo = 0;
		if(!t.isRoot())
			startNo = adjustNode(t);
		persistent(t, startNo);
		
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
	 * 调整节点
	 * @param parentId 父节点ID
	 * @return 调整后的节点序号，作为插入节点的开始编号
	 */
	protected long adjustNode(T t) {
		return 1;
	}
	
	/**
	 * 持久化消息记录
	 * @param message
	 */
	private void persistent(Message message, long startNo) {
		MessageRecord messageRecord = new MessageRecord();
		messageRecord.setContent(message.g);
		if(message.isRoot())
			
		
	}
	
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

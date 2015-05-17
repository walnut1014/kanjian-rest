package name.walnut.core;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import name.walnut.auth.dao.UserDao;
import name.walnut.auth.service.UserService;
import name.walnut.core.pojo.Message;
import name.walnut.relation.dao.FriendDao;

public abstract class AbstractSendMessage<T extends Message> implements SendMessage {

	@Override
	public void Send(Message message) {
		
		@SuppressWarnings("unchecked")
		final T t = (T)message;
		validate(t);
		long startNo =adjustNode(message.getPrentId());
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
		
		Set<Long> ids = friendDao.getFriendIds(senderId);
	}
	
	/**
	 * 调整节点
	 * @param parentId 父节点ID
	 * @return 调整后的节点序号，作为插入节点的开始编号
	 */
	private long adjustNode(long parentId) {
		return 0;
	}
	
	/**
	 * 持久化消息记录
	 * @param message
	 */
	private void persistent(Message message, long startNo) {
		
	}
	
	/**
	 * 个性化通知推送
	 * @param t
	 */
	protected abstract void pushCustomNotification(T t);
	
	@Autowired
	private FriendDao friendDao;

}

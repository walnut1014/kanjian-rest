package name.walnut.core.pojo;

import name.walnut.core.entity.MessageRecord;

/**
 * 回复消息
 * @author walnut
 *
 */
public class RepayMessage extends Message {

	public RepayMessage(String content, long prentId) {
		super(content);
		this.setPrentId(prentId);
	}
	
	public RepayMessage(MessageRecord messageReocrd, String sender) {
		super(messageReocrd, sender);
	}
	
	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	public Long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}

	private String receiver;
	private Long receiverId;

}

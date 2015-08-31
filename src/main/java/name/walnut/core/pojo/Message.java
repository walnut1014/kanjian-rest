package name.walnut.core.pojo;

import java.util.Date;

import name.walnut.controller.utils.OnlineUtils;
import name.walnut.core.entity.MessageRecord;

public abstract class Message {
	
	public Message(String content) {
		this.senderId = OnlineUtils.getOnlineUserId();
		this.content = content;
	}
	
	public Message(MessageRecord messageReocrd, String sender) {
		this.senderId = messageReocrd.getSenderId();
		this.sendTime = messageReocrd.getCreateDate();
		this.id = messageReocrd.getId();
		this.content = messageReocrd.getContent();
		this.prentId = messageReocrd.getParentId();
		this.sender = sender;
	}
	
	public boolean isRoot() {
		return prentId==null;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Long getSenderId() {
		return senderId;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public Long getPrentId() {
		return prentId;
	}
	public void setPrentId(Long prentId) {
		this.prentId = prentId;
	}
	
	public String getSender() {
		return sender;
	}

	public String getContent() {
		return content;
	}

	private long id;
	private Long senderId;
	private Date sendTime;
	private Long prentId;
	private String content;
	private String sender;
		
}

package name.walnut.core.pojo;

import java.util.Date;

import name.walnut.controller.utils.OnlineUtils;

public abstract class Message {
	
	public Message(String content) {
		this.senderId = OnlineUtils.getOnlineUserId();
		this.content = content;
	}
	
	public boolean isRoot() {
		return prentId==0;
	}
	public long getId() {
		return id;
	}
	public long getSenderId() {
		return senderId;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public long getPrentId() {
		return prentId;
	}
	public String getContent() {
		return content;
	}

	private long id;
	private long senderId;
	private Date sendTime;
	private long prentId;
	private String content;
		
}

package name.walnut.core.pojo;

import java.util.Date;

public abstract class Message {
	
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
	
	private long id;
	private long senderId;
	private Date sendTime;
	private long prentId;
		
}

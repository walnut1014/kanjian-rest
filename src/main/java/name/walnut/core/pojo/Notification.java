package name.walnut.core.pojo;

public class Notification {
	
	public Notification(){}
	
	public Notification(long messageId, long userId) {
		this.messageId = messageId;
		this.userId = userId;
		this.read = false;
	}
	
	public long getMessageId() {
		return messageId;
	}
	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public boolean isRead() {
		return read;
	}
	public void setRead(boolean read) {
		this.read = read;
	}
	
	public NotificationType getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(NotificationType notificationType) {
		this.notificationType = notificationType;
	}


	private long messageId;
	private long userId;
	private boolean read;
	private NotificationType notificationType;
	
}

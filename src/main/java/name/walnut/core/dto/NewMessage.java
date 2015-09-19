package name.walnut.core.dto;

import name.walnut.common.entity.User;

public class NewMessage {
	
	public User getRepayer() {
		return repayer;
	}
	public void setRepayer(User repayer) {
		this.repayer = repayer;
	}
	public String getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public long getMessageId() {
		return messageId;
	}
	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}
	public long getMessageSenderId() {
		return messageSenderId;
	}
	public void setMessageSenderId(long messageSenderId) {
		this.messageSenderId = messageSenderId;
	}




	private User repayer;
	private String photoPath;
	private String remark;
	private long messageId;
	private long messageSenderId;
}

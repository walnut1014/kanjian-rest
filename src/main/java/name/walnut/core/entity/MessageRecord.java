package name.walnut.core.entity;

import name.walnut.common.BaseEntity;

/**
 * 消息记录
 * @author walnut
 *
 */
public class MessageRecord extends BaseEntity {
	
	private long senderId;
	private String content;
	private String photoPath;
	private boolean reply;
	private long rootId;
	private long nodeLeft;
	private long nodeRight;
	
	public long getSenderId() {
		return senderId;
	}
	public void setSenderId(long senderId) {
		this.senderId = senderId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	public boolean isReply() {
		return reply;
	}
	public void setReply(boolean reply) {
		this.reply = reply;
	}
	public long getRootId() {
		return rootId;
	}
	public void setRootId(long rootId) {
		this.rootId = rootId;
	}
	public long getNodeLeft() {
		return nodeLeft;
	}
	public void setNodeLeft(long nodeLeft) {
		this.nodeLeft = nodeLeft;
	}
	public long getNodeRight() {
		return nodeRight;
	}
	public void setNodeRight(long nodeRight) {
		this.nodeRight = nodeRight;
	}
}

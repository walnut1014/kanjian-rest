package name.walnut.relation.entity;

import java.util.Date;

import name.walnut.common.BaseEntity;

public class Friend extends BaseEntity {
	
	private long userId;
	private long friendId;
	private Date createDate;
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getFriendId() {
		return friendId;
	}
	public void setFriendId(long friendId) {
		this.friendId = friendId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}

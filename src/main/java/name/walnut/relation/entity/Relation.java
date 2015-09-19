package name.walnut.relation.entity;

import name.walnut.common.entity.BaseEntity;

import java.util.Date;

/**
 * 用户关系对象
 * @author walnut
 *
 */
public class Relation extends BaseEntity {
	
	private long userId;
	private long relationId;
	private boolean agree;
	private boolean read;
	private Date createDate;
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getRelationId() {
		return relationId;
	}
	public void setRelationId(long relationId) {
		this.relationId = relationId;
	}
	public boolean isAgree() {
		return agree;
	}
	public void setAgree(boolean agree) {
		this.agree = agree;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public boolean isRead() {
		return read;
	}
	public void setRead(boolean read) {
		this.read = read;
	}

}

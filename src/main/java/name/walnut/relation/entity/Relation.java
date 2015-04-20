package name.walnut.relation.entity;

import java.util.Date;

import name.walnut.common.BaseEntity;
import name.walnut.relation.RelationStatus;

/**
 * 用户关系对象
 * @author walnut
 *
 */
public class Relation extends BaseEntity {
	
	private long userId;
	private long relationId;
	private RelationStatus relationStatus;
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
	public RelationStatus getRelationStatus() {
		return relationStatus;
	}
	public void setRelationStatus(RelationStatus relationStatus) {
		this.relationStatus = relationStatus;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}

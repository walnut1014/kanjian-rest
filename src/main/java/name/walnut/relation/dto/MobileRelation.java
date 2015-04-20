package name.walnut.relation.dto;

import name.walnut.relation.RelationStatus;

public class MobileRelation {
	
	private String mobilephone;
	private RelationStatus relationStatus;
	
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	public RelationStatus getRelationStatus() {
		return relationStatus;
	}
	public void setRelationStatus(RelationStatus relationStatus) {
		this.relationStatus = relationStatus;
	}
	
	
}

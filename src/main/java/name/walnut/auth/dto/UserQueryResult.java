package name.walnut.auth.dto;

import name.walnut.auth.RelationStatus;


public class UserQueryResult {
	
	private UserWithMobile userWithMobile;
	private Boolean invited = null;
	private Boolean agree = null;
	
	public UserQueryResult(UserWithMobile userWithMobile, boolean invited, boolean agree) {
		this.userWithMobile = userWithMobile;
		this.invited = invited;
		this.agree = agree;
	}
	
	public UserQueryResult(UserWithMobile userWithMobile) {
		this.userWithMobile = userWithMobile;
	}

	public String getNickName() {
		return userWithMobile.getNickName();
	}
	
	public String getHeadPhotoPath() {
		return userWithMobile.getHeadPhotoPath();
	}
	
	public long getId() {
		return userWithMobile.getId();
	}

	public String getMobilephone() {
		return userWithMobile.getMobilephone();
	}

	public int getRelationStatus() {
		if(agree==null)
			return RelationStatus.NO_RALATION;
		if(agree)
			return RelationStatus.AGREE;
		if(invited)
			return RelationStatus.WAIT_VERIFY;
		else
			return RelationStatus.ACCEPT;
	}
}

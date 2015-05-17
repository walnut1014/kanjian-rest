package name.walnut.relation.dto;

import name.walnut.auth.dto.UserWithMobile;

public class MobileRelation {
	
	private UserWithMobile userWithMobile;
	private boolean agree;
	private boolean invited;
	

	public MobileRelation(UserWithMobile userWithMobile, boolean agree,
							boolean invited) {
		this.userWithMobile = userWithMobile;
		this.agree = agree;
		this.invited = invited;
	}
	
	public boolean isAgree() {
		return agree;
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

	public boolean isInvited() {
		return invited;
	}

}

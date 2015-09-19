package name.walnut.auth.dto;

import name.walnut.common.entity.User;

public class UserWithMobile {
	private User user;
	private String Mobilephone;
	

	public String getNickName() {
		return user.getNickName();
	}
	
	public String getHeadPhotoPath() {
		return user.getHeadPhotoPath();
	}
	
	public long getId() {
		return user.getId();
	}

	public void setUser(User user) {
		this.user = user;
	}
	public String getMobilephone() {
		return Mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		Mobilephone = mobilephone;
	}
	
	
}

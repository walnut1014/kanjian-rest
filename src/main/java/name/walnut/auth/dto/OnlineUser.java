package name.walnut.auth.dto;

import name.walnut.common.entity.User;

public class OnlineUser {

	public OnlineUser(User user, int photoCount) {
		this.user = user;
		this.photoCount = photoCount;
	}


	public long getId() {return user.getId();}

	public String getMobilephone() {
		return user.getPhone();
	}

	public String getNickName() {
		return user.getNickName();
	}

	public String getHeadPhotoPath() {
		return user.getHeadPhotoPath();
	}

	public int getPhotoCount() {
		return photoCount;
	}

	private User user;
	private int photoCount;

}

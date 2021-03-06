package name.walnut.auth.entity;

import name.walnut.common.BaseEntity;
import name.walnut.common.Null;

public class User extends BaseEntity {

	private String nickName;
	private String headPhotoPath;
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeadPhotoPath() {
		return headPhotoPath;
	}

	public void setHeadPhotoPath(String headPhotoPath) {
		this.headPhotoPath = headPhotoPath;
	}
	
	private static class NullUser extends User implements Null {}
	public static final User NULL = new NullUser();

}

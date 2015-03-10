package name.walnut.auth.entity;

import name.walnut.common.BaseEntity;

public class AuthAccount extends BaseEntity {
	
	private String mobilephone;
	private String password;
	private String nickName;
	
	
	public AuthAccount() {}

	public AuthAccount(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
}

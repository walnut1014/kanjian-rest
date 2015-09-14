package name.walnut.auth.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import name.walnut.common.BaseEntity;
import name.walnut.common.Null;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(name="UK_MOBILEPHONE", columnNames="mobilephone")})
public class User extends BaseEntity {

	private String mobilephone;
	private String password;
	private String nickName;
	private String headPhotoPath;
	private Date createDate;
	
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	private static class NullUser extends User implements Null {}
	public static final User NULL = new NullUser();

}

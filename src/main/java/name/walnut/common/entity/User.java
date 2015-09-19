package name.walnut.common.entity;

import name.walnut.common.Null;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Set;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(name="UK_PHONE", columnNames="phone")})
public class User extends BaseEntity {

	private String phone;
	private String password;
	private String nickName;
	private String headPhotoPath;;

	public User() {}

	public User(long id) {
		setId(id);
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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

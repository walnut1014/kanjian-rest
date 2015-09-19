package name.walnut.common.entity;


import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Friend extends BaseEntity {

	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name="user_id",referencedColumnName="id", foreignKey = @ForeignKey(name="FK_USER_ID"))
	private User user;
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name="friend_id",referencedColumnName="id", foreignKey = @ForeignKey(name="FK_USER_FRIEND_ID"))
	private User friend;
	private Date createDate;

	public Friend() {}

	public Friend(long userId, long friendId) {
		this.user = new User(userId);
		this.friend = new User(friendId);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getFriend() {
		return friend;
	}

	public void setFriend(User friend) {
		this.friend = friend;
	}

	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}

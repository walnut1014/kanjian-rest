package name.walnut.controller.relation.vo;

public class RelationCount {
	
	private int unreadCount;
	private int friendsCount;
	
	
	
	public RelationCount(int unreadCount, int friendsCount) {
		this.unreadCount = unreadCount;
		this.friendsCount = friendsCount;
	}
	public int getUnreadCount() {
		return unreadCount;
	}
	public void setUnreadCount(int unreadCount) {
		this.unreadCount = unreadCount;
	}
	public int getFriendsCount() {
		return friendsCount;
	}
	public void setFriendsCount(int friendsCount) {
		this.friendsCount = friendsCount;
	}
	
	
}

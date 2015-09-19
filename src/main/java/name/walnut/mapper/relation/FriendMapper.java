package name.walnut.mapper.relation;

import name.walnut.relation.dto.FriendAndPhotoCount;
import name.walnut.common.entity.Friend;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;


public interface FriendMapper {

	@Insert("INSERT INTO t_friend(user_id,friend_id,create_date) "
			+ "VALUES("
			+ "#{userId},"
			+ "#{friendId},"
			+ "#{createDate})")
	void insert(Friend friend);
	
	/**
	 * 通过ID获得所有的朋友
	 * @param userId
	 * @return
	 */
	@Select("SELECT friend_id,a.mobilephone,u.nick_name,u.head_photo_path FROM t_friend f "
			+ "JOIN t_auth_account a ON f.friend_id = a.id "
			+ "JOIN t_user u on f.friend_id = u.id "
			+ "WHERE user_id = #{userId}")
	@Results({
			@Result(property="userId", column="friend_id", id=true),
			@Result(property="mobilephone", column="mobilephone"),
			@Result(property="nickName", column="nick_name"),
			@Result(property="headPhotoPath", column="head_photo_path")
	})
	List<FriendAndPhotoCount> getFriends(long userId);
	
	/**
	 * 获得好友个数
	 * @param userId
	 * @return
	 */
	@Select("SELECT COUNT(*) FROM t_friend WHERE user_id = #{userId}")
	int getFriendCount(long userId);
	
	/**
	 * 获得某用户的所有好友ID
	 * @param userId
	 * @return
	 */
	@Select("SELECT friend_id FROM t_friend WHERE user_id = #{userId}")
	Set<Long> getFriendIds(long userId);
}

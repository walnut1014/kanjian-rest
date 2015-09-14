package name.walnut.mapper.auth;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import name.walnut.auth.dto.UserWithMobile;
import name.walnut.auth.entity.User;


public interface UserMapper {

	@Insert("INSERT INTO t_user(id,nick_name,head_photo_path) "
			+ "VALUES("
			+ "#{id},"
			+ "#{nickName},"
			+ "#{headPhotoPath})")
	@Options(useGeneratedKeys=true, keyProperty= "id")
	long insert(User user);
	
	
	/**
	 * 通过IDs用户及手机号
	 * @param ids
	 * @return
	 */
	@Select("SELECT u.id,nick_name,mobilephone,head_photo_path "
			+ "FROM t_user u JOIN t_auth_account a ON a.id=u.id "
			+ "WHERE u.id IN ${ids}")
	@Results({
		@Result(property="user.id", column="id", id=true),
		@Result(property="user.headPhotoPath", column="head_photo_path"),
		@Result(property="user.nickName", column="nick_name"),
		@Result(property="mobilephone", column="mobilephone")
	})
	List<UserWithMobile> findUserByIds(@Param("ids") String ids);
	
	/**
	 * 通过手机号获得用户信息
	 * @param mobilephone
	 * @return
	 */
	@Select("SELECT u.id,u.head_photo_path,u.nick_name,a.mobilephone "
			+ "FROM t_auth_account a JOIN t_user u ON a.id = u.id "
			+ "WHERE a.mobilephone=#{mobilephone}")
	@Results({
			@Result(property="user.id", column="id", id=true),
			@Result(property="user.headPhotoPath", column="head_photo_path"),
			@Result(property="user.nickName", column="nick_name"),
			@Result(property="mobilephone", column="mobilephone")
		})
	UserWithMobile findUserByMobilephone(String mobilephone);
	
	/**
	 * 通过手机号获得用户信息
	 * @param mobilephone
	 * @return
	 */
	@Select("SELECT u.id,u.head_photo_path,u.nick_name,a.mobilephone "
			+ "FROM t_auth_account a JOIN t_user u ON a.id = u.id "
			+ "WHERE a.mobilephone IN ${mobilephones}")
	@Results({
			@Result(property="user.id", column="id", id=true),
			@Result(property="user.headPhotoPath", column="head_photo_path"),
			@Result(property="user.nickName", column="nick_name"),
			@Result(property="mobilephone", column="mobilephone")
		})
	List<UserWithMobile> findUserByMobilephones(@Param("mobilephones") String mobilephones);
	
	/**
	 * 更新用户昵称
	 * @param id
	 * @param nickName
	 */
	@Update("UPDATE t_user SET nick_name = #{nickName} WHERE id = #{id}")
	void updateNickName(@Param("id")long id, @Param("nickName")String nickName);
	
	/**
	 * 更新用户头像
	 * @param id
	 * @param nickName
	 */
	@Update("UPDATE t_user SET head_photo_path = #{path} WHERE id = #{id}")
	void updateHeadPhoto(@Param("id")long id, @Param("path")String path);
	
	@Update("UPDATE t_user SET last_select_photo_time = #{date} WHERE id = #{id}")
	void updateLastSelectTime(@Param("id")long id, @Param("date")Date date);
	
	/**
	 * 获得某用户发送的最后一条信息的发送时间
	 * @param userId 用户ID
	 * @return
	 */
	@Select("SELECT last_select_photo_time FROM t_user WHERE id = #{userId}")
	Date getLastSelectTime(long userId);
	
	@Select("SELECT id,nick_name,head_photo_path FROM t_user WHERE id = #{id}")
	@Results({
		@Result(property="id", column="id", id=true),
		@Result(property="headPhotoPath", column="head_photo_path"),
		@Result(property="nickName", column="nick_name")
	})
	User get(long id);
}

package name.walnut.mapper.auth;

import java.util.List;

import name.walnut.auth.dto.OnlineUser;
import name.walnut.auth.dto.UserWithMobile;
import name.walnut.auth.entity.AuthAccount;
import name.walnut.auth.entity.User;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


public interface UserMapper {

	@Insert("INSERT INTO t_user(id,nick_name,head_photo_path) "
			+ "VALUES("
			+ "#{id},"
			+ "#{nickName},"
			+ "#{headPhotoPath})")
	@Options(useGeneratedKeys=true, keyProperty= "id")
	long insert(User user);
	
	@Select("SELECT auth.id,mobilephone,nick_name,head_photo_path FROM t_auth_account "
			+ "auth JOIN t_user u ON auth.id = u.id "
			+ "WHERE mobilephone = #{mobilephone} "
			+ "AND `password` = #{password}")
	@Results({
			@Result(property="nickName", column="nick_name"),
			@Result(property="headPhotoPath", column="head_photo_path"),
			@Result(id=true, property="id", column="id")})
	OnlineUser getOnlineUser(AuthAccount authAccount);
	
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
}

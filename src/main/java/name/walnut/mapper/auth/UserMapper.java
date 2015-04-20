package name.walnut.mapper.auth;

import name.walnut.auth.entity.User;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;


public interface UserMapper {

	@Insert("INSERT INTO t_user(id,nick_name,head_photo_path) "
			+ "VALUES("
			+ "#{id},"
			+ "#{nickName},"
			+ "#{headPhotoPath})")
	@Options(useGeneratedKeys=true, keyProperty= "id")
	long insert(User user);

}

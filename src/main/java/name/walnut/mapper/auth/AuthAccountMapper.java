package name.walnut.mapper.auth;

import name.walnut.auth.entity.AuthAccount;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface AuthAccountMapper {

	@Select("SELECT * FROM t_auth_account WHERE id = #{id}")
	AuthAccount get(int id);
	
	@Insert("INSERT INTO t_auth_account(mobilephone,create_date) VALUES("
			+ "#{mobilephone},"
			+ "#{createDate})")
	void insert(AuthAccount authAccount);
	
	@Select("SELECT count(*) FROM t_auth_account WHERE mobilephone = #{mobilephone}")
	int getCountByMobilephone(String mobilephone);
	
	@Select("SELECT password FROM t_auth_account WHERE mobilephone = #{mobilephone}")
	String getPasswordByMobilephone(String mobilephone);

}

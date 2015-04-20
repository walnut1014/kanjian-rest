package name.walnut.mapper.auth;

import name.walnut.auth.entity.AuthAccount;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface AuthAccountMapper {

	@Select("SELECT * FROM t_auth_account WHERE id = #{id}")
	AuthAccount get(int id);
	
	@Insert("INSERT INTO t_auth_account(mobilephone,password,create_date) "
			+ "VALUES("
			+ "#{mobilephone},"
			+ "#{password},"
			+ "#{createDate})")
	@Options(useGeneratedKeys=true, keyProperty="id")
	void insert(AuthAccount authAccount);
	
	@Select("SELECT count(*) FROM t_auth_account WHERE mobilephone = #{mobilephone}")
	int getCountByMobilephone(String mobilephone);
	
	@Select("SELECT id,mobilephone,password FROM t_auth_account WHERE mobilephone = #{mobilephone}")
	AuthAccount getAuthAccountByMobilephone(String mobilephone);
	
	@Update("UPDATE t_auth_account SET password=#{password} WHERE mobilephone = #{mobilephone}")
	void updatePassowrdByMobilephone(@Param("mobilephone") String mobilephone, @Param("password")String password);

}

package name.walnut.mapper.relation;

import name.walnut.relation.entity.Relation;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;


public interface RelationMapper {

	@Insert("INSERT INTO t_relation(user_id,relation_id,relation_status,create_date) "
			+ "VALUES("
			+ "#{userId},"
			+ "#{relationId},"
			+ "#{relationStatus, typeHandler=org.apache.ibatis.type.EnumOrdinalTypeHandler},"
			+ "#{createDate})")
	void insert(Relation relation);
	
	/**
	 * 获得等待验自己验证的请求数
	 * @param userId
	 * @return
	 */
	@Select("SELECT COUNT(*) FROM t_relation WHERE relation_id = #{userId} AND relation_status = 1")
	int getWaitVerifyCount(long userId);
	
	int getAgreedCount(long userId);
	
	/**
	 * 获得好友个数
	 * @param userId
	 * @return
	 */
	@Select("SELECT COUNT(*) FROM t_relation WHERE user_id = #{userId} OR relation_id= #{usrId} AND relation_status = 2")
	int getFriendCount(long userId);

}

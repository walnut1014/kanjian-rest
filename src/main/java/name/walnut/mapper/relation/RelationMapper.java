package name.walnut.mapper.relation;

import java.util.List;

import name.walnut.relation.entity.Relation;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


public interface RelationMapper {

	@Insert("INSERT INTO t_relation(user_id,relation_id,is_agree,create_date) "
			+ "VALUES("
			+ "#{userId},"
			+ "#{relationId},"
			+ "#{agree},"
			+ "#{createDate})")
	void insert(Relation relation);
	
	/**
	 * 获得未读消息数
	 * @param userId
	 * @return
	 */
	@Select("SELECT COUNT(*) FROM t_relation "
			+ "WHERE (user_id = #{userId} OR relation_id =  #{userId}) AND is_read = false")
	int getUnreadCount(long userId);
	
	
	/**
	 * 获得所有的用户关系，包括推荐别人，以及被推荐的
	 * @param userId
	 * @return
	 */
	@Select("SELECT id,user_id,relation_id,is_agree FROM t_relation "
			+ "WHERE user_id = #{userId} OR relation_id = #{relationId} ORDER BY create_data DESC")
	@Results({
		@Result(column="user_id", property="userId"),
		@Result(column="relation_id", property="relationId"),
		@Result(column="is_agree", property="agree")
	})
	List<Relation> getRelations(long userId);
	
	
	/**
	 * 更新id集合为已读
	 * @param ids
	 */
	@Update("UPDATE t_relation SET is_read=true WHERE id IN ${ids}")
	void updateRead(@Param("ids")String ids);
	
	/**
	 * 通过查询条件获得ID
	 * @param relation
	 * @return
	 */
	@Select("SELECT id FROM t_relation WHERE "
			+ "user_id =#{userId} AND "
			+ "relation_id=#{relationId} AND "
			+ "is_agree = #{agree}")
	Long findId(Relation relation);
	
	/**
	 * 通过查询条件获得Relation
	 * @param relation
	 * @return
	 */
	@Select("SELECT id,user_id,relation_id,is_agree FROM t_relation WHERE "
			+ "user_id =#{userId} AND "
			+ "relation_id=#{relationId}")
	@Results({
		@Result(column="user_id", property="userId"),
		@Result(column="relation_id", property="relationId"),
		@Result(column="is_agree", property="agree")
	})
	Relation findRelation(Relation query);
	
	/**
	 * 更新状态
	 * @param id
	 * @param relationStatus
	 */
	@Update("UPDATE t_relation SET is_agree=#{agree} WHERE id = #{id}")
	void updateAgree(@Param("id")long id, @Param("agree")boolean agree);
	

}

package name.walnut.mapper.core;

import name.walnut.common.entity.User;
import name.walnut.core.entity.MessageRecord;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface MessageRecordMapper {

	/**
	 * 插入消息记录
	 * @param messageRecourd
	 */
	@Insert("INSERT INTO t_message_record(id," + SqlConst.MESSAGE_RECORD_COLUMN + ",batch_id)"
			+ "VALUES("
			+ "#{id},"
			+ "#{senderId},"
			+ "#{content},"
			+ "#{photoPath},"
			+ "#{reply},"
			+ "#{createDate},"
			+ "#{parentId},"
			+ "#{batchId})")
	void insert(MessageRecord messageRecord);
	
	
	@Select("SELECT id," + SqlConst.MESSAGE_RECORD_COLUMN + ",batch_id FROM t_message_record "
			+ "WHERE id = #{id}")
	@Results({
		@Result(property="id", column="id", id=true),
		@Result(property="senderId", column="sender_id"),
		@Result(property="content", column="content"),
		@Result(property="photoPath", column="photo_path"),
		@Result(property="reply", column="is_reply"),
		@Result(property="createDate", column="create_date"),
		@Result(property="parentId", column="parent_id"),
		@Result(property="batchId", column="batch_id")})
	MessageRecord get(long id);

	@Delete("DELETE FROM t_message_record WHERE batch_id = #{id}")
	void deleteByBatch(long id);
	
	@Select("SELECT count(*) FROM t_message_record WHERE sender_id = #{senderId}")
	int getPhotoCountBySenderId(long senderId);
	
	@Select("SELECT id," + SqlConst.MESSAGE_RECORD_COLUMN + " FROM t_message_record "
			+ "WHERE sender_id IN ${ids} AND is_reply = false "
			+ "ORDER BY create_date DESC LIMIT ${start}, ${count}")
	@Results({
		@Result(property="id", column="id", id=true),
		@Result(property="senderId", column="sender_id"),
		@Result(property="content", column="content"),
		@Result(property="photoPath", column="photo_path"),
		@Result(property="reply", column="is_reply"),
		@Result(property="createDate", column="create_date"),
		@Result(property="parentId", column="parent_id"),
		@Result(property="batchId", column="batch_id")})
	List<MessageRecord> findMainMessageRecordByIds(@Param("ids")String ids, 
												   @Param("start")int start, 
												   @Param("count")int count);
	
	
	@Select("SELECT id,content,parent_id,create_date,batch_id,sender_id FROM t_message_record "
			+ "WHERE batch_id IN ${ids} AND is_reply = true "
			+ "ORDER BY create_date DESC;")
	@Results({
		@Result(property="id", column="id", id=true),
		@Result(property="senderId", column="sender_id"),
		@Result(property="content", column="content"),
		@Result(property="photoPath", column="photo_path"),
		@Result(property="reply", column="is_reply"),
		@Result(property="createDate", column="create_date"),
		@Result(property="parentId", column="parent_id"),
		@Result(property="batchId", column="batch_id")})
	List<MessageRecord> findRepayMessageRecordByIds(@Param("ids")String ids);
	
	@Select("SELECT DISTINCT sender_id,nick_name FROM t_message_record m "
			+ "JOIN t_user u ON m.sender_id = u.id WHERE batch_id IN ${ids};")
	@Results({
		@Result(property="id", column="sender_id", id=true),
		@Result(property="nickName", column="nick_name")})
	List<User> findNichNameByBatchId(@Param("ids")String ids);
	
	@Select("SELECT DISTINCT friend_id FROM t_message_record m "
			+ "JOIN t_friend f ON f.friend_id = m.sender_id "
			+ "WHERE batch_id = #{param1} and f.user_id = #{param2}")
	List<Long> findBatchFriendId(long batchId, long userId);
	
}	

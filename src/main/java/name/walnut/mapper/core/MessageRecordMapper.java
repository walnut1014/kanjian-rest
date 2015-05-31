package name.walnut.mapper.core;

import java.util.Date;

import name.walnut.core.entity.MessageRecord;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;


public interface MessageRecordMapper {

	/**
	 * 插入消息记录
	 * @param messageRecourd
	 */
	@Insert("INSERT INTO t_message_record(id," + SqlConst.MESSAGE_RECORD_COLUMN + ")"
			+ "VALUES("
			+ "#{id},"
			+ "#{senderId},"
			+ "#{content},"
			+ "#{photoPath},"
			+ "#{reply},"
			+ "#{createDate})")
	void insert(MessageRecord messageRecord);
	
	
	@Select("SELECT id," + SqlConst.MESSAGE_RECORD_COLUMN + " FROM t_message_record "
			+ "WHERE id = #{id}")
	MessageRecord get(long id);

	@Delete("DELETE FROM t_message_record WHERE id = #{id}")
	void delete(long id);
	
	/**
	 * 获得某用户发送的最后一条信息的发送时间
	 * @param userId 用户ID
	 * @return
	 */
	@Select("SELECT create_date FROM t_message_record "
			+ "WHERE sender_id = #{userId} AND is_reply = false "
			+ "ORDER BY create_date DESC LIMIT 1")
	Date getLastPhotoSendTime(long userId);
	
}	

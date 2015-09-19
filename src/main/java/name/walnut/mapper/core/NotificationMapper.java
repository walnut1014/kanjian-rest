package name.walnut.mapper.core;

import name.walnut.core.pojo.Notification;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface NotificationMapper {
	
	@InsertProvider(method = "insert", type = NotificationSqlProvider.class)
	void insert(List<Notification> ids);
	
	@Select("SELECT count(*) FROM t_notification WHERE is_read = false AND user_id = #{userId}")
	int getUnReadCount(long userId);
	
	@Select("SELECT message_id,user_id FROM t_notification "
			+ "WHERE is_read = false AND user_id = #{userId} AND notification_type = 0")
	@Results({
		@Result(property="messageId", column="message_id", id=true),
		@Result(property="userId", column="user_id", id=true)})
	List<Notification> getNewNotification(long userId);
	
	@Delete("DELETE FROM t_notification WHERE "
			+ "message_id IN (SELECT id FROM"
			+ "(SELECT id FROM t_message_record WHERE	batch_id = #{param1} AND is_reply=true) s)")
	void deleteByBatchId(long messageId);
	
	@Update("UPDATE t_notification SET is_read = true "
			+ "WHERE message_id = #{param1} AND user_id = #{param2}")
	void updateRead(long messageId, long userId);
}

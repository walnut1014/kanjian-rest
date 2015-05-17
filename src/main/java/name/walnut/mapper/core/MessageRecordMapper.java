package name.walnut.mapper.core;

import name.walnut.core.entity.MessageRecord;

import org.apache.ibatis.annotations.Insert;


public interface MessageRecordMapper {

	/**
	 * 插入消息记录
	 * @param messageRecourd
	 */
	@Insert("INSERT INTO t_message_recourd(send_id,content,photo_path,is_reply,"
			+ "main_message_id,node_start_no,node_end_no,create_date) "
			+ "VALUES("
			+ "#{senderId},"
			+ "#{content},"
			+ "#{photoPath},"
			+ "#{isReply},"
			+ "#{mainMessageId},"
			+ "#{nodeStartNo},"
			+ "#{nodeEndNo},"
			+ "#{createDate})")
	void insert(MessageRecord messageRecourd);	
}	

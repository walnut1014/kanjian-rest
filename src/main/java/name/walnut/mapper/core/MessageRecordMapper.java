package name.walnut.mapper.core;

import name.walnut.core.entity.MessageRecord;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;


public interface MessageRecordMapper {

	/**
	 * 插入消息记录
	 * @param messageRecourd
	 */
	@Insert("INSERT INTO t_message_recourd(" + SqlConst.MESSAGE_RECORD_COLUMN + ")"
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
	
	
	@Select("SELECT id," + SqlConst.MESSAGE_RECORD_COLUMN + " FROM t_message_record "
			+ "WHERE id = #{id}")
	MessageRecord get(long id);
}	

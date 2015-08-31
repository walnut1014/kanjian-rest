package name.walnut.core.service;

import java.util.List;

import name.walnut.common.Page;
import name.walnut.core.pojo.MainMessage;
import name.walnut.core.pojo.Message;

public interface MessageService {
	/**
	 * 删除消息记录
	 * @param id
	 */
	void deleteMessage(long id);
	
	/**
	 * 获得用户最后一次选照片距离24小时还有多长时间
	 * @return
	 */
	long getLastSelectResidueTimeTo24();
	
	/**
	 * 设置用户选择照片时间
	 */
	void setSelectPhotoTime();
	
	/**
	 * 获得用户消息分页信息
	 * @param page
	 */
	List<Message> getAllMessage(Page page);
	
	/**
	 * 
	 * @param prantId
	 * @return
	 */
	MainMessage getBatchMessage(long prantId);
	
	/**
	 * 获得个人相册的分页信息
	 * @param page
	 * @return
	 */
	List<Message> getSelfMessage(Page page, long userId);
}

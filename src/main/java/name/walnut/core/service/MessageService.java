package name.walnut.core.service;

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
	
}

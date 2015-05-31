package name.walnut.core.service.impl;

import java.util.Date;

import name.walnut.auth.dao.UserDao;
import name.walnut.common.BusinessException;
import name.walnut.controller.utils.OnlineUtils;
import name.walnut.core.dao.MessageRecordDao;
import name.walnut.core.entity.MessageRecord;
import name.walnut.core.service.MessageService;
import name.walnut.utils.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

	@Override
	public void deleteMessage(long id) {
		
		MessageRecord messageRecord = messageRecordDao.getMapper().get(id);
		if(messageRecord.getSenderId() != OnlineUtils.getOnlineUserId())
			throw new BusinessException("无权限操作");
		if(DateUtils.DAY- messageRecord.getCreateDate().getTime() > 0)
			throw new BusinessException("不能删除未超过24小时的照片");
		
		messageRecordDao.getMapper().delete(id);
	}
	
	@Override
	public long getLastSelectResidueTimeTo24() {
		
		Date lastSendTime = messageRecordDao.getMapper().getLastPhotoSendTime(OnlineUtils
														.getOnlineUserId());
		if(lastSendTime == null)
			return 0;
		
		final long diffTime =DateUtils.DAY 
								- DateUtils.getDifference(DateUtils.getNow(), lastSendTime);
		if(diffTime < 0)
			return 0;
		return diffTime;
	}
	
	@Override
	public void setSelectPhotoTime() {
		userDao.getMapper().updateLastSelectTime(OnlineUtils.getOnlineUserId(), DateUtils.getNow());
	}
	
	@Autowired
	private MessageRecordDao messageRecordDao;
	
	@Autowired
	private UserDao userDao;
}

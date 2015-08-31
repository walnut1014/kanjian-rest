package name.walnut.core.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import name.walnut.auth.dao.UserDao;
import name.walnut.common.BusinessException;
import name.walnut.common.Page;
import name.walnut.controller.utils.OnlineUtils;
import name.walnut.core.dao.MessageRecordDao;
import name.walnut.core.dao.NotificationDao;
import name.walnut.core.entity.MessageRecord;
import name.walnut.core.pojo.MainMessage;
import name.walnut.core.pojo.Message;
import name.walnut.core.pojo.RepayMessage;
import name.walnut.core.service.MessageService;
import name.walnut.relation.dao.FriendDao;
import name.walnut.utils.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class MessageServiceImpl implements MessageService {

	@Override
	public void deleteMessage(long id) {
		
		MessageRecord messageRecord = messageRecordDao.getMapper().get(id);
		if(messageRecord.isReply())
			throw new BusinessException("无权限操作");
		if(messageRecord.getSenderId() != OnlineUtils.getOnlineUserId())
			throw new BusinessException("无权限操作");
		if(DateUtils.DAY- messageRecord.getCreateDate().getTime() > 0)
			throw new BusinessException("不能删除未超过24小时的照片");
		
		notificationDao.deleteByBatchId(id);
		messageRecordDao.getMapper().deleteByBatch(id);
	}
	
	@Override
	public long getLastSelectResidueTimeTo24() {
		
		Date lastSendTime = userDao.getMapper().getLastSelectTime(OnlineUtils
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
	
	@Override
	public List<Message> getAllMessage(Page page) {

		Set<Long> ids = friendDao.getFriendIds(OnlineUtils.getOnlineUserId());
		ids.add(OnlineUtils.getOnlineUserId());
		return buildMessage(page, ids);
	}
	
	@Override
	public MainMessage getBatchMessage(long batchId) {
		
		Set<Long> mainIds = new HashSet<>();
		mainIds.add(batchId);
		Map<Long, List<MessageRecord>> repayMap = messageRecordDao.findRepayMessageRecordByIds(mainIds);
		Map<Long, String> nickNames = messageRecordDao.findNichNameByBatchId(mainIds);
		
		MessageRecord messageRecord = messageRecordDao.getMainMessageRecordById(batchId);
		
		MainMessage message = new MainMessage(messageRecord, nickNames.get(messageRecord.getSenderId()));
		message.setHeadPath(userDao.get(messageRecord.getSenderId()).getHeadPhotoPath());
		if(repayMap.containsKey(message.getId()))
			buildRepayMessage(message, repayMap.get(message.getId()), nickNames);

		return message;
	}
	
	@Override
	public List<Message> getSelfMessage(Page page, long userId) {
		
		Set<Long> ids = new HashSet<>();
		ids.add(userId);
		return buildMessage(page, ids);
	}

	private List<Message> buildMessage(Page page, Set<Long> ids) {
		
		List<Message> result = new ArrayList<>();
		List<MessageRecord> messageRecords = messageRecordDao.findMainMessageRecordByIds(ids, page);
		if(CollectionUtils.isEmpty(messageRecords))
			return result;
	
		Set<Long> mainIds = new HashSet<>();
		for(MessageRecord record : messageRecords)
			mainIds.add(record.getId());
		Map<Long, List<MessageRecord>> repayMap = messageRecordDao.findRepayMessageRecordByIds(mainIds);
		Map<Long, String> nickNames = messageRecordDao.findNichNameByBatchId(mainIds);
		
		for(MessageRecord record : messageRecords) {
			MainMessage message = new MainMessage(record, nickNames.get(record.getSenderId()));
			message.setHeadPath(userDao.get(record.getSenderId()).getHeadPhotoPath());
			if(repayMap.containsKey(message.getId()))
				buildRepayMessage(message, repayMap.get(message.getId()), nickNames);
			result.add(message);
		}
		return result;
	}
	
	private void buildRepayMessage(MainMessage main, List<MessageRecord> messageRecords, 
									Map<Long, String> nickNames) {
		for(MessageRecord record : messageRecords) {
			RepayMessage repayMessage = new RepayMessage(record, nickNames.get(record.getSenderId()));
			if(repayMessage.getPrentId() != main.getId()) {
				setReceiver(repayMessage, messageRecords,
						 repayMessage.getPrentId(),nickNames);
			}
			main.addRepayMessage(repayMessage);
		}
	}
	
	private void setReceiver(RepayMessage repayMessage, List<MessageRecord> messageRecords, 
								long id, Map<Long, String> nickNames) {
		for(MessageRecord record : messageRecords) {
			if(record.getId() == id){
				repayMessage.setReceiver(nickNames.get(record.getSenderId()));
				repayMessage.setReceiverId(record.getSenderId());
				return;
			}
		}
		throw new BusinessException("系统错误");
	}
	
	@Autowired
	private MessageRecordDao messageRecordDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private FriendDao friendDao;
	
	@Autowired
	private NotificationDao notificationDao;
	
}

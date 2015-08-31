package name.walnut.core.service;

import java.util.List;

import name.walnut.core.dto.NewMessage;

public interface NotificationService {
	
	int getCount();
	
	List<NewMessage> getNewMessage();
	
	void readMessage(long messageId);
}

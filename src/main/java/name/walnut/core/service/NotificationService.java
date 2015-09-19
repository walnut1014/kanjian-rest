package name.walnut.core.service;

import name.walnut.core.dto.NewMessage;

import java.util.List;

public interface NotificationService {
	
	int getCount();
	
	List<NewMessage> getNewMessage();
	
	void readMessage(long messageId);
}

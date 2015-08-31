package name.walnut.controller.core;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import name.walnut.core.dto.NewMessage;
import name.walnut.core.service.NotificationService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationController {
	
	@RequestMapping(value = "count", method= RequestMethod.GET)
	public int getCount() {
		return notificationService.getCount();
	}
	
	@RequestMapping(value="newMessage", method= RequestMethod.GET)
	public List<NewMessage> newMessageList() {
		return notificationService.getNewMessage();
	}
	
	@RequestMapping(value="read", method= RequestMethod.POST)
	public void readNotification(Map<String, Long> param) {
		notificationService.readMessage(param.get("messageId"));
	}
	
	@Resource
	private NotificationService notificationService;
}

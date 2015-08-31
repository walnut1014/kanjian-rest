package name.walnut.controller.core;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import name.walnut.common.Page;
import name.walnut.controller.utils.OnlineUtils;
import name.walnut.core.pojo.Message;
import name.walnut.core.service.MessageService;
import name.walnut.web.vo.Normal;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class MainController {
	
	@RequestMapping(value = "/message/main", method= RequestMethod.GET)
	public List<Message> getMainMessage(@RequestParam("page") int pageNo) {
		
		
		return messageService.getAllMessage(new Page(pageNo));
	}
	
	@RequestMapping(value = "/message/self", method= RequestMethod.GET)
	public List<Message> getSelfMessage(@RequestParam("page") int pageNo) {
		
		return messageService.getSelfMessage(new Page(pageNo), OnlineUtils.getOnlineUserId());
	}
	
	@RequestMapping(value = "/message", method= RequestMethod.GET)
	public List<Message> getUserMessage(@RequestParam("page") int pageNo, 
			@RequestParam("userId") long userId) {
		
		return messageService.getSelfMessage(new Page(pageNo), userId);
	}
	
	@RequestMapping(value = "/message/delete", method= RequestMethod.POST)
	public Normal deleteMessage(@RequestBody Map<String, String> map) {
		
		messageService.deleteMessage(Long.valueOf(map.get("id")));
		return Normal.INSTANCE;
	}
	
	@Resource
	private MessageService messageService;
}

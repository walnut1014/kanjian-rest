package name.walnut.controller.core;

import name.walnut.core.SendMessage;
import name.walnut.core.pojo.MainMessage;
import name.walnut.core.pojo.RepayMessage;
import name.walnut.core.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 评论Controller
 * @author walnut
 *
 */
@RestController
@RequestMapping("/message/repay")
public class RepayController {
	
	@RequestMapping(method=RequestMethod.POST)
	public MainMessage repay(@RequestBody Map<String, Object> param) {
		
		long parentId = Long.parseLong(param.get("id").toString());
		
		RepayMessage repayMessage = new RepayMessage(param.get("content").toString(), 
													parentId);
		long batchId = repayMessageSender.Send(repayMessage);
		
		return messageService.getBatchMessage(batchId);
	}
	
	@Resource(name="repayMessageSender")
	private SendMessage repayMessageSender;
	
	@Autowired
	private MessageService messageService;
}

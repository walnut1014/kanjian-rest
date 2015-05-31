package name.walnut.controller.core;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 评论Controller
 * @author walnut
 *
 */
@RestController
@RequestMapping("/message/repay")
public class RepayController {
	
	@RequestMapping(method=RequestMethod.POST)
	public void repay(@RequestParam("id") long id, 
					  @RequestParam("content") String content) {
		
	}
}

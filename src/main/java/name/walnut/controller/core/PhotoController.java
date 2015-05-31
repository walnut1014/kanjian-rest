package name.walnut.controller.core;

import javax.annotation.Resource;

import name.walnut.controller.Const;
import name.walnut.controller.utils.UploadUtils;
import name.walnut.core.SendMessage;
import name.walnut.core.pojo.MainMessage;
import name.walnut.core.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/message/photo")
public class PhotoController {
	
	@RequestMapping(method=RequestMethod.POST)
	public void upload(@RequestParam("photo") MultipartFile photo, 
					   @RequestParam("content") String content) {
		
		Assert.isTrue(!photo.isEmpty());
		MainMessage mainMessage = new MainMessage(content, 
										UploadUtils.upload(photo, Const.PHOTO_GROUP));
		mainMessageSender.Send(mainMessage);
	}
	
	@RequestMapping(value = "residueTime", method=RequestMethod.GET)
	public long getResidueTime() {
		return messageService.getLastSelectResidueTimeTo24();
	}
	
	@RequestMapping(value = "delete", method=RequestMethod.POST)
	public void delete(@RequestParam("id") long id) {
		messageService.deleteMessage(id);
	}
	
	@RequestMapping(value="selectTime", method=RequestMethod.POST)
	public void setSelectTime() {
		messageService.setSelectPhotoTime();
	}
	
	@Resource(name="mainMessageSender")
	private SendMessage mainMessageSender;
	
	@Autowired
	private MessageService messageService;
}

package name.walnut.core.pojo;

import java.util.ArrayList;
import java.util.List;

import name.walnut.core.entity.MessageRecord;

/**
 * 带照片的主消息
 * @author walnut
 *
 */
public class MainMessage extends Message {
	
	public MainMessage(String content, String photoPath) {
		super(content);
		this.photoPath = photoPath;
	}
	
	public MainMessage(MessageRecord messageReocrd, String sender) {
		super(messageReocrd, sender);
		this.photoPath = messageReocrd.getPhotoPath();
	}

	public String getPhotoPath() {
		return photoPath;
	}
	
	public List<RepayMessage> getRepayMessages() {
		return repayMessages;
	}

	public void addRepayMessage(RepayMessage repayMessage) {
		this.repayMessages.add(repayMessage);
	}
	
	public String getHeadPath() {
		return headPath;
	}

	public void setHeadPath(String headPath) {
		this.headPath = headPath;
	}




	private String photoPath;
	private String headPath;
	
	private List<RepayMessage> repayMessages = new ArrayList<>();
}

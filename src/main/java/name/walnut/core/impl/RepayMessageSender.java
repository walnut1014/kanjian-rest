package name.walnut.core.impl;

import name.walnut.core.AbstractMessageSender;
import name.walnut.core.entity.MessageRecord;
import name.walnut.core.pojo.RepayMessage;

public class RepayMessageSender extends AbstractMessageSender<RepayMessage> {

	@Override
	protected void validate(RepayMessage message) {
		
	}

	@Override
	protected void customBuild(MessageRecord messageRecord, RepayMessage message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void pushCustomNotification(RepayMessage t) {
		// TODO Auto-generated method stub
		
	}

}

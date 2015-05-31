package name.walnut.sequence;

import name.walnut.sequence.impl.Sequence;
import name.walnut.utils.SpringUtils;

/**
 * 序列枚举
 * 需要在数据库中添加
 * 枚举名和ST_SEQ_STATE表中的SEQ_NAME相同
 * @author walnut
 *
 */

	public enum SequenceEnum {
		//消息记录
		MESSAGE_RECORD;
		
	public long next(){
		SequenceManager manager = SpringUtils.getBean("sequenceManager");
		Sequence sequence =  manager.getSequence(this);
		return sequence.next();
	}
	
	public long current(){
		SequenceManager manager = SpringUtils.getBean("sequenceManager");
		return manager.getSequence(this).getCurrent();
	}
}

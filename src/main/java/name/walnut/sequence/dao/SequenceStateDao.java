package name.walnut.sequence.dao;

import name.walnut.common.BaseDao;
import name.walnut.sequence.SequenceEnum;
import name.walnut.sequence.impl.Sequence;
import name.walnut.sequence.pojo.SequenceState;



public interface SequenceStateDao extends BaseDao {
	
	SequenceState getSequenceState(String name);
	
	
	void update(SequenceState state);
	
	
	public Sequence getNewSequence(SequenceEnum seqEnum);
}

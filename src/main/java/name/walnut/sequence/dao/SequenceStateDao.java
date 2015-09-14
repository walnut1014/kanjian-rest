package name.walnut.sequence.dao;

import name.walnut.common.DeprecatedBaseDao;
import name.walnut.mapper.sequence.SequenceStateMapper;
import name.walnut.sequence.SequenceEnum;
import name.walnut.sequence.entity.SequenceState;
import name.walnut.sequence.impl.Sequence;



public interface SequenceStateDao extends DeprecatedBaseDao<SequenceStateMapper> {
	
	SequenceState getSequenceState(String name);
	
	
	void update(SequenceState state);
	
	
	public Sequence getNewSequence(SequenceEnum seqEnum);
}

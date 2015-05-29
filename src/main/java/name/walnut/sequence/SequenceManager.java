package name.walnut.sequence;

import com.daren.common.sequence.impl.Sequence;

public interface SequenceManager {
	
	Sequence getSequence(SequenceEnum sequence);
	
}

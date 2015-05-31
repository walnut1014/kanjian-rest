package name.walnut.sequence;

import name.walnut.sequence.impl.Sequence;

public interface SequenceManager {
	
	Sequence getSequence(SequenceEnum sequence);
	
}

package name.walnut.sequence.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daren.common.sequence.SequenceEnum;
import com.daren.common.sequence.SequenceManager;
import com.daren.common.sequence.dao.SequenceStateDao;

@Service("sequenceManager")
public class SequenceManagerImpl implements SequenceManager {

	@Autowired
	private SequenceStateDao sequenceStateDao;
	
	private final Map<SequenceEnum,Sequence> seqMap = new ConcurrentHashMap<SequenceEnum,Sequence>();
	

	@Override
	public Sequence getSequence(SequenceEnum seqEnum) {
		
		Sequence sequence = null;
		synchronized (seqEnum) {
			
			if(!seqMap.containsKey(seqEnum) || (sequence=seqMap.get(seqEnum)).depletedCache()){
				seqMap.put(seqEnum, sequence= sequenceStateDao.getNewSequence(seqEnum));
			}
		}
		return sequence;
	}
	

}

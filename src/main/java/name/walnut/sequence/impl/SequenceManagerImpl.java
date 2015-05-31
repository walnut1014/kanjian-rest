package name.walnut.sequence.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import name.walnut.sequence.SequenceEnum;
import name.walnut.sequence.SequenceManager;
import name.walnut.sequence.dao.SequenceStateDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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

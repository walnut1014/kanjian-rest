package name.walnut.sequence.dao.impl;

import name.walnut.mapper.sequence.SequenceStateMapper;
import name.walnut.sequence.SequenceEnum;
import name.walnut.sequence.dao.SequenceStateDao;
import name.walnut.sequence.entity.SequenceState;
import name.walnut.sequence.impl.Sequence;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;



@Repository
public class SequenceStateDaoImpl implements SequenceStateDao {
	
	@Override
	public SequenceState getSequenceState(String name) {
		
		return sequenceStateMapper.selectByName(name);
	}

	
	@Override
	public void update(SequenceState state){
		sequenceStateMapper.update(state.getName(), state.getCurrentValue());
	}


	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public Sequence getNewSequence(SequenceEnum seqEnum){
		
		SequenceState state = this.getSequenceState(seqEnum.name());
		if(state == null){
			RuntimeException ex = new RuntimeException("没有找到名为"+seqEnum.name()+"的序列");
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
		state.setCurrentValue(state.getCurrentValue()+state.getCacheSize()*state.getStep());
		this.update(state);
		logger.info(seqEnum.name()+"从数据库中重新分配"+state.getCacheSize()+"个步长为"+state.getStep()+"的值");
		return new Sequence(state);
	}
	
	@Override
	public SequenceStateMapper getMapper() {
		return sequenceStateMapper;
	}

	@Autowired
	private SequenceStateMapper sequenceStateMapper;
	
	private final Logger logger = Logger.getLogger(SequenceStateDaoImpl.class);


}

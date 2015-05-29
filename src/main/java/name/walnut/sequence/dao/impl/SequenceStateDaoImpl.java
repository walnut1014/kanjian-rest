package name.walnut.sequence.dao.impl;

import name.walnut.sequence.SequenceEnum;
import name.walnut.sequence.dao.SequenceStateDao;
import name.walnut.sequence.impl.Sequence;
import name.walnut.sequence.pojo.SequenceState;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;



@Repository
public class SequenceStateDaoImpl implements SequenceStateDao {
	
	@Override
	public SequenceState getSequenceState(String name) {
		
		SequenceState state = sqlSession.selectOne(NAMESPACE+"selectByName",name);
		return state;
	}

	
	@Override
	public void update(SequenceState state){
		sqlSession.update(NAMESPACE+ALIAS_UPDATE,state);
	}


	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public Sequence getNewSequence(SequenceEnum seqEnum){
		
		SequenceState state = this.getSequenceState(seqEnum.name());
		if(state == null){
			
			String msg = "没有找到名为"+seqEnum.name()+"的序列";
			DaoException ex = new DaoException(msg);
			logger.error(msg, ex);
			throw ex;
		}
		state.setCurrentValue(state.getCurrentValue()+state.getCacheSize()*state.getStep());
		this.update(state);
		logger.info(seqEnum.name()+"从数据库中重新分配"+state.getCacheSize()+"个步长为"+state.getStep()+"的值");
		return new Sequence(state);
	}
	
	private static final String NAMESPACE = "common.sequenceStateMapper.";
	
	private final Logger logger = Logger.getLogger(SequenceStateDaoImpl.class);

	@Override
	public Object getMapper() {
		// TODO Auto-generated method stub
		return null;
	}



}

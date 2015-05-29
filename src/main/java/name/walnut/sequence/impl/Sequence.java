package name.walnut.sequence.impl;

import org.apache.log4j.Logger;

import com.daren.common.sequence.SequenceType;
import com.daren.common.sequence.exception.SequenceException;
import com.daren.common.sequence.pojo.SequenceState;

public class Sequence {

	public Sequence(SequenceState state) {
		
		this.state = state;
		this.currentVal = state.getCurrentValue() - state.getCacheSize()*state.getStep();
		this.cacheVal = this.currentVal + state.getCacheSize()*state.getStep();
	}

	public synchronized long next() {

		currentVal+=state.getStep();
		if (SequenceType.getMaxValue(state.getType()) == this.currentVal)
			throw new SequenceException("序列已到最大值！");
		logger.debug(String.format("{sequenceName:%s; createValue:%d; threadId:%d}", this.getName(), this.currentVal,
				Thread.currentThread().getId()));
		return currentVal;
	}

	public synchronized boolean depletedCache() {
		return this.currentVal+this.state.getStep()>this.cacheVal;
	}

	public synchronized long getCurrent() {
		return currentVal;
	}

	public long getMaxVal() {
		return state.getMaxVal();
	}

	public SequenceType getSequenceType() {
		return state.getType();
	}

	public String getName() {
		return state.getName();
	}

	private SequenceState state;

	private volatile long currentVal;

	private long cacheVal;

	private final Logger logger = Logger.getLogger(Sequence.class);
	

}

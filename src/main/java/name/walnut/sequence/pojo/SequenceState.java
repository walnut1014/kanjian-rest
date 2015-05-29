package name.walnut.sequence.pojo;

import com.daren.common.sequence.SequenceType;
import com.daren.common.utils.JsonUtils;


public class SequenceState{
	private String name;
	private String comment;
	private int step;
	private int initValue;
	private int cacheSize;
	private long currentValue;
	private SequenceType type;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public int getInitValue() {
		return initValue;
	}
	public void setInitValue(int initValue) {
		this.initValue = initValue;
	}
	public int getCacheSize() {
		return cacheSize;
	}
	public void setCacheSize(int cacheSize) {
		this.cacheSize = cacheSize;
	}
	public SequenceType getType() {
		return type;
	}
	public void setType(SequenceType type) {
		this.type = type;
	}
	public long getCurrentValue() {
		return currentValue;
	}
	public void setCurrentValue(long currentValue) {
		this.currentValue = currentValue;
	}
	public long getMaxVal(){
		if(this.type==null)
			return 0;
		if(this.type.equals(SequenceType.LONG))
			return Long.MAX_VALUE;
		else
			return Integer.MAX_VALUE;
	}
	@Override
	public String toString() {
		return JsonUtils.getJSON(this);
	}
	
	
	

}

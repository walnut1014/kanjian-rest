package name.walnut.sequence.entity;



public class SequenceState{
	private String name;
	private String comment;
	private int step;
	private int initValue;
	private int cacheSize;
	private long currentValue;
	
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
	
	public long getCurrentValue() {
		return currentValue;
	}
	public void setCurrentValue(long currentValue) {
		this.currentValue = currentValue;
	}
	public long getMaxVal(){
		return Long.MAX_VALUE;
	}
}

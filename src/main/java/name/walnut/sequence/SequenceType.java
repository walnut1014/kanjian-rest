package name.walnut.sequence;

public enum SequenceType {
	INT,LONG;
	
	
	public static long getMaxValue(SequenceType type){
		if(type==INT)
			return Integer.MAX_VALUE;
		else
			return Long.MAX_VALUE;
	}
}

package name.walnut.support;

final class StringHelper {
	/**
	 * @param str
	 * @return
	 */
	public static String beforeCapital(String str) {
		
		final char[] array = str.toCharArray();
		final StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<array.length; i++) {
			if(i!=0 && Character.isUpperCase(array[i]))
				sb.append("_"+Character.toLowerCase(array[i]));
			else
				sb.append(Character.toLowerCase(array[i]));
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(beforeCapital("UserActiom"));
	}
}

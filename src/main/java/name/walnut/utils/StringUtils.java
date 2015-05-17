package name.walnut.utils;

import java.util.Random;
import java.util.Set;

/**
 * 达人贷字符串工具，扩展自org.apache.commons.lang3
 * @author walnut
 *
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
	
	/**
	 * 获得随机生成的字符串
	 * @param length 生成字符串的长度
	 * @return 返回随机生成的字符串
	 */
	public static String getRandomStr(int length) {
		
		final String allChar = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ~!@#$%^&*()_+";
		StringBuilder sb = new StringBuilder();
		
		Random random = new Random();
		for (int i = 0; i < length; ++i)
			sb.append(allChar.charAt(random.nextInt(allChar.length())));
		return sb.toString();
	}
	
	/**
	 * 获得IN字句字符串
	 * @param ids ID集合
	 * @return
	 */
	public static String getInString(Set<?> ids) {
		StringBuilder sb = new StringBuilder("(");
		for(Object l : ids){
			sb.append(l.toString());
			sb.append(",");
		}
		sb.replace(sb.length()-1, sb.length(), ")");
		return sb.toString();
	}

}

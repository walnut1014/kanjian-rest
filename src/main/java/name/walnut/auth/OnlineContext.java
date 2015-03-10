package name.walnut.auth;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum OnlineContext {
	
	INSTANCE;
	
	public void addLoginAuth(OnlineAccount account) {
		map.put(account.getMobilephone(), account);
	}
	
	public void addLoginAuth(String mobilephone) {
		map.remove(mobilephone);
	}
	
	public OnlineAccount getLoginAuth(String mobilephone) {
		return map.get(mobilephone);
	}
	
	private Map<String, OnlineAccount> map = new ConcurrentHashMap<>();
}

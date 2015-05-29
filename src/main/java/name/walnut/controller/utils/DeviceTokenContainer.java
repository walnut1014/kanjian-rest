package name.walnut.controller.utils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public enum DeviceTokenContainer {
	
	INSTANCE;
	
	public void putDeviceToken(long id, String deviceToken) {
		deviceTokenMap.put(id, deviceToken);
	}
	
	public Set<String> find(Set<Long> friendIds) {
		
		Set<String> tokenSet = new HashSet<>();
		for(Long id :friendIds) {
			tokenSet.add(deviceTokenMap.get(id));
		}
		return tokenSet;
	}
	
	
	private Map<Long, String> deviceTokenMap = new ConcurrentHashMap<>();


	
}

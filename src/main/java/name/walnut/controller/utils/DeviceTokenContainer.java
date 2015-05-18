package name.walnut.controller.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum DeviceTokenContainer {
	
	INSTANCE;
	
	public void putDeviceToken(long id, String deviceToken) {
		deviceTokenMap.put(id, deviceToken);
	}
	
	private Map<Long, String> deviceTokenMap = new ConcurrentHashMap<>();
}

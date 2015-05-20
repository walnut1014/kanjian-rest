package name.walnut.push;

import java.util.HashSet;
import java.util.Set;

import org.apache.ibatis.builder.BuilderException;

import com.alibaba.fastjson.JSONObject;

public abstract class PushRequest extends JSONObject {

	public PushRequest(PushType pushType, Object data) {
		this.data = data;
		this.put("appkey", Const.UMENG_APP_KEY);
		this.put("timestamp", System.currentTimeMillis());
		this.put("type", pushType.toString());
		
		JSONObject payload = generatePayload();
		this.put("payload", payload);
	}
	
	@Override
	public String toJSONString() {
		this.put("device_tokens", buildDeviceTokensString());
		return super.toJSONString();
	}
	
	public void addDeviceToken(String deviceToken) {
		this.deviceTokens.add(deviceToken);
	}
	
	protected Object getData() {
		return data;
	}
	
	/**
	 * 创建payload
	 * @return
	 */
	protected abstract JSONObject generatePayload();
	
	private String buildDeviceTokensString() {
		
		if(deviceTokens.size() == 0)
			throw new BuilderException("必须要有指定的设备号才可以发送");
		
		StringBuilder sb = new StringBuilder();
		for(String s : deviceTokens) {
			sb.append(s);
			sb.append(",");
		}
		sb.deleteCharAt(sb.length()-1);
		
		return sb.toString();
	}
	
	/** 设备编号集合*/
	private Set<String> deviceTokens = new HashSet<>();
	
	/** 发送的数据*/
	private Object data;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3016272127956683795L;

}

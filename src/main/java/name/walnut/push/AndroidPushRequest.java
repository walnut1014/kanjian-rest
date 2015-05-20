package name.walnut.push;

import com.alibaba.fastjson.JSONObject;

public class AndroidPushRequest extends PushRequest {

	public AndroidPushRequest(PushType pushType, Object data) {
		super(pushType, data);
	}

	@Override
	protected JSONObject generatePayload() {
		JSONObject payload = new JSONObject();
		payload.put("display_type", "message");
		
		JSONObject body = new JSONObject();
		body.put("custom", getData());
		payload.put("body", body);
		
		return payload;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4206336570762073081L;
}

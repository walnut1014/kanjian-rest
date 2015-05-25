package name.walnut.push;

import com.alibaba.fastjson.JSONObject;

public class IosPushRequest extends PushRequest {

	public IosPushRequest(PushType pushType, Object data) {
		super(pushType, data);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected JSONObject generatePayload() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8115317643087277481L;

}

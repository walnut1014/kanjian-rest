package name.walnut.push;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;

public enum PushHelp {
	
	INSTANCE;
	
	public void push(Set<String> deviceTokens, Object data) {
		AndroidPushRequest androidRequest = new AndroidPushRequest(PushType.listcast, data);
		IosPushRequest iosRequest = new IosPushRequest(PushType.listcast, data);
		
		int androidCount = 0,
			iosCount = 0;
		for(String s : deviceTokens) {
			if(s.length()==44){
				androidRequest.addDeviceToken(s);
				if(++androidCount==500){
					executePush(androidRequest);
					androidRequest.resetDeviceToken();
				}
			}else{
				iosRequest.addDeviceToken(s);
				iosCount++;
				if(++iosCount==500){
					executePush(iosRequest);
					iosRequest.resetDeviceToken();
				}
			}
			
			if(androidRequest.hasDeviceToken())
				executePush(androidRequest);
			if(iosRequest.hasDeviceToken())
				executePush(iosRequest);
		}
	}
	
	public void push(String deviceToken, Object data) {
		
		PushRequest pushRequest = deviceToken.length() == 44 
					? new AndroidPushRequest(PushType.unicast, data) 
					: new IosPushRequest(PushType.unicast, data);
		pushRequest.addDeviceToken(deviceToken);
		
		executePush(pushRequest);
	}
	
	private void executePush(PushRequest pushRequest) {
		
		String sign = getSign(pushRequest.toJSONString());
		try(CloseableHttpClient client = HttpClientBuilder.create().build()){
			
			HttpPost post = new HttpPost(Const.UMENG_URL+"?sign="+sign);
			
	        StringEntity se = new StringEntity(pushRequest.toJSONString(), "UTF-8");
	        post.setEntity(se);
	        
	        HttpResponse response = client.execute(post);
			
			if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
				 BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				 StringBuffer result = new StringBuffer();
				 String line = "";
				 while ((line = rd.readLine()) != null) {
		            result.append(line);
				 }
				logger.error("友盟推送失败！"+result.toString());
			}
		} catch (IOException e) {
			logger.error("消息推送发送错误", e);
		}
	}
	
	private String getSign(String json) {
		String result = null;
		 try {
			 result = DigestUtils.md5Hex(("POST" + Const.UMENG_URL + json + Const.UMENG_APP_MASTER_SECRET).getBytes("utf8"));
		} catch (UnsupportedEncodingException e) {
			logger.error("系统错误", e);
		}
		return result;
	}
	
	//TODO 测试
	public static void main(String[] args) {
		//"AqPDuoIEO8kfFp0eJjMeH5sb4wzfBcEYK_n_xd5a-tG2"
		
		PushHelp.INSTANCE.push("AqPDuoIEO8kfFp0eJjMeH5sb4wzfBcEYK_n_xd5a-tG2", "aa");
	}

	private Logger logger = Logger.getLogger(PushHelp.class);
	
}

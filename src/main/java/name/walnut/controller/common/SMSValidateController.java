package name.walnut.controller.common;

import com.alibaba.fastjson.JSON;
import name.walnut.common.BusinessException;
import name.walnut.controller.Const;
import name.walnut.utils.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.net.ssl.*;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Map;

@RestController
public class SMSValidateController {

	@RequestMapping(value = "smsvalidate", method = RequestMethod.POST)
	public String sendValidateResult(@RequestBody Map<String, String> param, HttpSession session) {
		
		validate(param.get("phone"), param.get("zone"), param.get("code"));
		String token = StringUtils.getRandomStr(32);
		session.setAttribute(Const.MESSAGE_TOKEN, token);
		return token;
	}
	
	private void validate(String phone, String zone, String code) {

		String params = "appkey=" + appkey + "&phone=" + phone + "&zone="
				+ zone + "&code=" + code;

		HttpURLConnection conn = null;
		try {
			// Create a trust manager that does not validate certificate chains
			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				public void checkClientTrusted(X509Certificate[] certs,
						String authType) {
				}

				public void checkServerTrusted(X509Certificate[] certs,
						String authType) {
				}
			} };

			// Install the all-trusting trust manager
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, trustAllCerts, new SecureRandom());

			// ip host verify
			HostnameVerifier hv = new HostnameVerifier() {
				@Override
				public boolean verify(String urlHostName, SSLSession session) {
					return urlHostName.equals(session.getPeerHost());
				}
			};

			// set ip host verify
			HttpsURLConnection.setDefaultHostnameVerifier(hv);

			HttpsURLConnection
					.setDefaultSSLSocketFactory(sc.getSocketFactory());

			URL url = new URL(smsUrl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");// POST
			conn.setConnectTimeout(3000);
			conn.setReadTimeout(3000);
			// set params ;post params
			if (params != null) {
				conn.setDoOutput(true);
				DataOutputStream out = new DataOutputStream(
						conn.getOutputStream());
				out.write(params.getBytes(Charset.forName("UTF-8")));
				out.flush();
				out.close();
			}
			conn.connect();
			// get result
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				logger.error(conn.getResponseCode() + ":"
						+ conn.getResponseMessage());
				throw new BusinessException("发送短信失败", -2);
			} else {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(conn.getInputStream()));
				StringBuffer buffer = new StringBuffer();
				String line = null;
				boolean first = true;
				while ((line = reader.readLine()) != null) {
					if (first) {
						first = false;
					} else {
						buffer.append("\n");
					}
					buffer.append(line);
				}
				int valiCode = JSON.parseObject(buffer.toString()).getInteger(
						"status");
				switch (valiCode) {
				case 200:
					break;
				case 520:
					throw new BusinessException("验证码错误", -1);
				default:
					logger.error("短信服务商报错" + valiCode);
					throw new BusinessException("验证码错误", -2);
				}
			}
		} catch (IOException | KeyManagementException
				| NoSuchAlgorithmException e) {
			logger.error("系统错误", e);
			throw new BusinessException("发送短信失败", -2);
		} finally {
			if (conn != null)
				conn.disconnect();
		}
	}
	
	
	private final static String appkey = "673e4d512b90";
	
	private final static String smsUrl = "https://api.sms.mob.com/sms/verify";
	
	
	private static Logger logger = Logger.getLogger(SMSValidateController.class);
}

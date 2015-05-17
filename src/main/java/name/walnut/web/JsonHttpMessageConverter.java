package name.walnut.web;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import name.walnut.common.Null;
import name.walnut.web.vo.Normal;
import name.walnut.web.vo.SimpleType;

import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

public class JsonHttpMessageConverter extends FastJsonHttpMessageConverter {
	
	@Override
	protected void writeInternal(Object obj, HttpOutputMessage outputMessage) throws IOException,
			HttpMessageNotWritableException {
		
		OutputStream out = outputMessage.getBody();

		if(obj instanceof SimpleType)
			out.write(handleSimpleType(obj));
		else if(obj instanceof Normal || obj instanceof Null)
			out.write(handleNormal());
		else
			out.write(handleOthers(obj));
	}
	
	/**
	 * 处理简单类型的数据
	 * @param result
	 * @return
	 */
	private byte[] handleSimpleType(Object result) {
		
		return JSON.toJSONString(buildResultMap(((SimpleType)result).getValue()))
														.getBytes(this.getCharset());
	}
	
	/**
	 * 处理简单类型的数据
	 * @param result
	 * @return
	 */
	private byte[] handleNormal() {
		
		return JSON.toJSONString(buildResultMap()).getBytes(this.getCharset());
	}
	
	/**
	 * 处理其他任意类型数据
	 * @param obj
	 * @return
	 */
	private byte[] handleOthers(Object obj) {
		
		return JSON.toJSONString(buildResultMap(obj)).getBytes(this.getCharset());
	}
	
	private Map<String, Object> buildResultMap(Object value) {
		
		Map<String, Object> map = buildResultMap();
		map.put("data", value);
		return map;
	}
	
	private Map<String, Object> buildResultMap() {
		
		Map<String, Object> map = new HashMap<>(2);
		map.put("success", true);
		return map;
	}
	
	
}
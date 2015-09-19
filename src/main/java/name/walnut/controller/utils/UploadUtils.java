package name.walnut.controller.utils;

import name.walnut.utils.SpringUtils;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * 文件上传工具
 * @author walnut
 *
 */
public class UploadUtils {
	
	/**
	 * 上传文件方法
	 * 
	 * 文件名为系统时间戳
	 * @param mFile 文件
	 * @param group 组名称
	 * @return 组名称+文件名
	 */
	public static String upload(MultipartFile mFile, String group) {

		String path = getUploadPath()+group+"/";
		String fileName = String.valueOf(System.currentTimeMillis());
		try {
			FileUtils.copyInputStreamToFile(mFile.getInputStream(), new File(
					path, fileName));
			
			logger.info("文件名为["+fileName+"]的文件上传到"+path);
		} catch (IOException e) {
			logger.error("系统错误", e);
		}
		
		return  group+ "/" + fileName;
	}
	
	/**
	 * 获得上传文件的路径
	 * @return
	 */
	public static String getUploadPath() {
		
		Properties properties = SpringUtils.getBean("kanjianConf");
		return properties.getProperty("uploadPath") + "/";
	}

	private static Logger logger = Logger.getLogger(UploadUtils.class);
}

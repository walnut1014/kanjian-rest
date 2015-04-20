package name.walnut.controller.utils;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import name.walnut.utils.SpringUtils;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传工具
 * @author walnut
 *
 */
public class UploadUtils {
	
	public static void upload(MultipartFile mFile, String fileName) {

		Properties properties = SpringUtils.getBean("kanjianConf");
		
		try {
			FileUtils.copyInputStreamToFile(mFile.getInputStream(), new File(
					properties.getProperty("uploadPath"), fileName));
			
			logger.info("文件名为["+fileName+"]的文件上传到"+properties.getProperty("uploadPath"));
		} catch (IOException e) {
			logger.error("系统错误", e);
		}
	}

	private static Logger logger = Logger.getLogger(UploadUtils.class);
}

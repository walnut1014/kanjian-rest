package name.walnut.controller.common;

import name.walnut.controller.Const;
import name.walnut.utils.SpringUtils;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

@Controller
@RequestMapping(Const.DOWLOAD_PAHT)
public class FileDownloadController {

	@RequestMapping(value = "head_photo/{path}", method = RequestMethod.GET)
	public void getHeadPhoto(@PathVariable("path") String path,
			HttpServletResponse response) {
		Properties properties = SpringUtils.getBean("kanjianConf");
		try {
			FileUtils.copyFile(new File(properties.getProperty("uploadPath")
					+ "/" + Const.HEAD_PHOTO_GROUP + "/" + path),
					response.getOutputStream());
		} catch (IOException e) {
			logger.error("系统错误", e);
		}
	}
	
	@RequestMapping(value = "photo_group/{path}", method = RequestMethod.GET)
	public void getPhoto(@PathVariable("path") String path,
			HttpServletResponse response) {
		Properties properties = SpringUtils.getBean("kanjianConf");
		try {
			FileUtils.copyFile(new File(properties.getProperty("uploadPath")
					+ "/" + Const.PHOTO_GROUP + "/" + path),
					response.getOutputStream());
		} catch (IOException e) {
			logger.error("系统错误", e);
		}
	}

	private Logger logger = Logger.getLogger(FileDownloadController.class);
}

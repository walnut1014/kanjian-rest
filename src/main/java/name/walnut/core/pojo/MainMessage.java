package name.walnut.core.pojo;

/**
 * 带照片的主消息
 * @author walnut
 *
 */
public class MainMessage extends Message {
	
	
	
	public MainMessage(String content, String photoPath) {
		super(content);
		this.photoPath = photoPath;
	}

	private String photoPath;

	public String getPhotoPath() {
		return photoPath;
	}
}

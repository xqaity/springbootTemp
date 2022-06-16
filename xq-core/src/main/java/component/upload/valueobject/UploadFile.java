package component.upload.valueobject;/**
 * @author Created by lenovo
 * @date 2022/6/6 15:13
 */

/**
 * <h3>xq-mode</h3>
 * <p>上传文件信息</p>
 *
 * @author : xq
 * @date : 2022-06-06 15:13
 **/
public class UploadFile {
	private String fileName;
	private String url;

	public UploadFile(String fileName, String url) {
		this.fileName = fileName;
		this.url = url;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}

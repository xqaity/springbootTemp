package component.upload.valueobject;/**
 * @author Created by lenovo
 * @date 2022/6/6 15:14
 */

/**
 * <h3>xq-mode</h3>
 * <p>响应消息</p>
 *
 * @author : xq
 * @date : 2022-06-06 15:14
 **/
public class Message {

	private String message;

	public Message(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

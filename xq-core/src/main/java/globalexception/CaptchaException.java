package globalexception;

/**
 * @author Created by lenovo
 * @date 2022/5/25 11:19
 */
public class CaptchaException extends Exception{
	public CaptchaException(String res){
		super(res);
	}
}

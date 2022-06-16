package globalexception;

/**
 * @author Created by lenovo
 * @date 2022/5/24 11:55
 */
public class RepeatSubmitException extends Exception{
	public RepeatSubmitException(){
		super("接口限流步长异常");
	}
}

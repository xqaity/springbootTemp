package globalexception;

/**
 * @author Created by lenovo
 * @date 2022/5/24 11:54
 */
public class ServiceException extends RuntimeException{
	public ServiceException(){
		super();
	}
	public ServiceException(String e){
		super(e);
	}
}

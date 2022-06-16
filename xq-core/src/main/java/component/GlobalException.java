package component;

//import component.vo.Result;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import component.upload.valueobject.Message;
import component.vo.Result;
import globalexception.ServiceException;
import globalexception.CaptchaException;
import globalexception.RepeatSubmitException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.boot.context.properties.bind.BindException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import utils.ResultUtil;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Created by lenovo
 * @date 2022/5/24 11:53
 */
@Slf4j
@RestControllerAdvice
public class GlobalException {
	@ExceptionHandler(ServiceException.class)
	public Map<String, Object> serviceException(ServiceException e) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("status", 600);
		map.put("message", e.getMessage());
		return map;
	}

	/**
	 * @param e 重复点击异常
	 * @return
	 */
	@ExceptionHandler(RepeatSubmitException.class)
	public Map<String, Object> runtimeException(RepeatSubmitException e) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("status", 601);
		map.put("message", e.getMessage());
		return map;
	}

	/**
	 * @param e 数据校验异常
	 * @return
	 */
	@SneakyThrows
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Result handlerVaildException(MethodArgumentNotValidException e){
		log.error("数据校验出现问题{}","异常类型：{}",e.getMessage(),e.getClass());
		BindingResult bindingResult = e.getBindingResult();
		List<Object> errorList = new ArrayList<>();
		bindingResult.getFieldErrors().forEach(fieldError -> {
			errorList.add(fieldError);
		});
		String defaultMessage = bindingResult.getFieldError().getDefaultMessage();

		return ResultUtil.error(560,new ObjectMapper().writeValueAsString(errorList)+defaultMessage);
	}

	@ExceptionHandler(CaptchaException.class)
	public  Map<String, Object> captchaException(CaptchaException e) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("status", 602);
		map.put("message", e.getMessage());
		return map;
	}
	@ExceptionHandler(JsonProcessingException.class)
	public  Map<String, Object> jsonProcessingException(JsonProcessingException e) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("status", 561);
		map.put("message", e.getMessage());
		return map;
	}
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<Message> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e){
		return ResponseEntity.badRequest().body(new Message("Upload file too large."));
	}

	private HttpStatus getStatus(HttpServletRequest request) {
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		if (statusCode == null) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return HttpStatus.valueOf(statusCode);
	}

	// 捕捉shiro的异常
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(ShiroException.class)
	public Result handle401(ShiroException e) {
		return new ResultUtil<Integer>().setData(401, e.getMessage());
	}


	/**
	 * 捕获一些进入controller之前的异常，有些4xx的状态码统一设置为200
	 *
	 * @param ex
	 * @return
	 */
	@ExceptionHandler({HttpRequestMethodNotSupportedException.class,
			HttpMediaTypeNotSupportedException.class, HttpMediaTypeNotAcceptableException.class,
			MissingPathVariableException.class, MissingServletRequestParameterException.class,
			ServletRequestBindingException.class, ConversionNotSupportedException.class,
			TypeMismatchException.class, HttpMessageNotReadableException.class,
			HttpMessageNotWritableException.class,
			MissingServletRequestPartException.class, BindException.class,
			NoHandlerFoundException.class, AsyncRequestTimeoutException.class})
	public Map<String, Object> onException(Exception ex) {
		//打印日志
		log.error(ex.getMessage());
		//todo 日志入库等等操作
		HashMap<String, Object> map = new HashMap<>();
		map.put("status", 799);
		map.put("message", ex.getMessage());
		return map;
		//统一结果返回
	}
}

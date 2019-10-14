package club.xiaoandx.commons.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import club.xiaoandx.commons.core.ErrorCode;




/**
 * 统一异常处理
 * @author zhen.pan
 *
 */
@ResponseStatus(HttpStatus.OK)
@RestControllerAdvice
@Slf4j
public class ExceptionHandle {
	/**
	 * 通用异常
	 * @param e	 异常
	 * @return 转换后的异常
	 */
	@ExceptionHandler(CommonException.class)
	public ExceptionMessage doRequiredExceptionHandle(CommonException e) {
		return new ExceptionMessage(e.getCode(), e.getMessage());
	}
	/**
	 * 未授权异常
	 * @param e 异常
	 * @return	转换后的异常
	 */
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(NoAuthenticationException.class)
	public ExceptionMessage doNoAuthenticationException(NoAuthenticationException e) {
		return new ExceptionMessage(e.getCode(), e.getMessage());
	}

	/*************************************DefaultHandlerExceptionResolver*************************************************************/
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ExceptionMessage doHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
		log.warn("HttpRequestMethodNotSupportedException:{}", e);
		return new ExceptionMessage(ErrorCode.SPRING_EXCEPTION_CODE, e.getMessage());
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ExceptionMessage doHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
		log.warn("HttpMediaTypeNotSupportedException:{}", e);
		return new ExceptionMessage(ErrorCode.SPRING_EXCEPTION_CODE, e.getMessage());
	}

	@ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
	public ExceptionMessage doHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException e) {
		log.warn("HttpMediaTypeNotAcceptableException:{}", e);
		return new ExceptionMessage(ErrorCode.SPRING_EXCEPTION_CODE, e.getMessage());
	}

	@ExceptionHandler(MissingPathVariableException.class)
	public ExceptionMessage doMissingPathVariableException(MissingPathVariableException e) {
		log.warn("MissingPathVariableException:{}", e);
		return new ExceptionMessage(ErrorCode.SPRING_EXCEPTION_CODE, e.getMessage());
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ExceptionMessage doMissingServletRequestParameterException(MissingServletRequestParameterException e) {
		log.warn("MissingServletRequestParameterException:{}", e);
		return new ExceptionMessage(ErrorCode.SPRING_EXCEPTION_CODE, e.getMessage());
	}

	@ExceptionHandler(ServletRequestBindingException.class)
	public ExceptionMessage doServletRequestBindingException(ServletRequestBindingException e) {
		log.warn("ServletRequestBindingException:{}", e);
		return new ExceptionMessage(ErrorCode.SPRING_EXCEPTION_CODE, e.getMessage());
	}

	@ExceptionHandler(ConversionNotSupportedException.class)
	public ExceptionMessage doConversionNotSupportedException(ConversionNotSupportedException e) {
		log.warn("ConversionNotSupportedException:{}", e);
		return new ExceptionMessage(ErrorCode.SPRING_EXCEPTION_CODE, e.getMessage());
	}

	@ExceptionHandler(TypeMismatchException.class)
	public ExceptionMessage doTypeMismatchException(TypeMismatchException e) {
		log.warn("TypeMismatchException:{}", e);
		return new ExceptionMessage(ErrorCode.SPRING_EXCEPTION_CODE, e.getMessage());
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ExceptionMessage doHttpMessageNotReadableException(HttpMessageNotReadableException e) {
		log.warn("HttpMessageNotReadableException:{}", e);
		return new ExceptionMessage(ErrorCode.SPRING_EXCEPTION_CODE, e.getMessage());
	}

	@ExceptionHandler(HttpMessageNotWritableException.class)
	public ExceptionMessage doHttpMessageNotWritableException(HttpMessageNotWritableException e) {
		log.warn("HttpMessageNotWritableException:{}", e);
		return new ExceptionMessage(ErrorCode.SPRING_EXCEPTION_CODE, e.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ExceptionMessage doMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		log.warn("MethodArgumentNotValidException:{}", e);
		return new ExceptionMessage(ErrorCode.SPRING_EXCEPTION_CODE, e.getMessage());
	}

	@ExceptionHandler(MissingServletRequestPartException.class)
	public ExceptionMessage doMissingServletRequestPartException(MissingServletRequestPartException e) {
		log.warn("MissingServletRequestPartException:{}", e);
		return new ExceptionMessage(ErrorCode.SPRING_EXCEPTION_CODE, e.getMessage());
	}

	@ExceptionHandler(BindException.class)
	public ExceptionMessage doBindException(BindException e) {
		log.warn("BindException:{}", e);
		return new ExceptionMessage(ErrorCode.SPRING_EXCEPTION_CODE, e.getMessage());
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoHandlerFoundException.class)
	public ExceptionMessage doNoHandlerFoundException(NoHandlerFoundException e) {
		log.warn("NoHandlerFoundException:{}", e);
		return new ExceptionMessage(ErrorCode.SPRING_EXCEPTION_CODE, e.getMessage());
	}

	@ExceptionHandler(AsyncRequestTimeoutException.class)
	public ExceptionMessage doAsyncRequestTimeoutException(AsyncRequestTimeoutException e) {
		log.warn("AsyncRequestTimeoutException:{}", e);
		return new ExceptionMessage(ErrorCode.SPRING_EXCEPTION_CODE, e.getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(Exception.class)
	public ExceptionMessage doException(Exception e) {
		log.warn("Exception:{}", e);
		return new ExceptionMessage(ErrorCode.SYSTEM_EXCEPTION_CODE, null != e ? e.getMessage(): "NullPointerException");
	}
}

package mutu.sprest.logging.exception;

import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpStatus;

/**
 * @author Zaw Than Oo
 * @since 01-DEC-2018 <br/>
 *        This classed is used to throw exception for business logic.
 */
public class BusinessLogicException extends NestedRuntimeException {
	private static final long serialVersionUID = -4636343176401289427L;
	private String errorCode;
	private Object response;
	private HttpStatus httpStatus;

	public BusinessLogicException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public BusinessLogicException(String errorCode, String message, HttpStatus httpStatus) {
		super(message);
		this.errorCode = errorCode;
		this.httpStatus = httpStatus;
	}

	public BusinessLogicException(String errorCode, String message, Throwable throwable) {
		super(message, throwable);
		this.errorCode = errorCode;
	}

	public BusinessLogicException(String errorCode, Object response, String message) {
		super(message);
		this.errorCode = errorCode;
		this.response = response;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public Object getResponse() {
		return response;
	}
	
	public void setResponse(Object response) {
		this.response = response;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}

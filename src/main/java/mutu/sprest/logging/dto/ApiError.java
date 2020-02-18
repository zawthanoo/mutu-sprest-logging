package mutu.sprest.logging.dto;

import java.util.Date;

/**
 * @author Zaw Than Oo
 * @since 01-DEC-2018 <br/>
 *        This classed is used as response entity to the client when the system
 *        throw an exception.
 */

public class ApiError {
	private String messageCode;
	private String message;
	private Object payLoad;
	private Date timestamp;

	public ApiError() {
		timestamp = new Date();
	}

	public ApiError(String message, String messageCode) {
		this();
		this.message = message;
		this.messageCode = messageCode;
	}
	
	public ApiError(String message, String messageCode, Object payLoad) {
		this();
		this.message = message;
		this.messageCode = messageCode;
		this.payLoad = payLoad;
	}

	public String getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getPayLoad() {
		return payLoad;
	}

	public void setPayLoad(Object payLoad) {
		this.payLoad = payLoad;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
}
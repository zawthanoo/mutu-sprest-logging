package mutu.sprest.logging.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * @author Zaw Than Oo
 * @since 01-DEC-2018 <br/>
 *        This classed is used as response entity to the client when the system
 *        throw an exception.
 */

@Data
public class ApiError {
	private String messageCode;
	private String message;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Date timestamp;
	private Object payLoad;

	public ApiError() {
		timestamp = new Date();
	}

	public ApiError(Throwable ex) {
		this();
		this.message = "Unexpected error";
	}

	public ApiError(String message, String messageCode, Throwable ex) {
		this();
		this.message = message;
	}
}
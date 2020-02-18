package mutu.sprest.logging.dto;

import java.util.List;

public class LogHttpResponseEntity {
	private String type;
	private String method;
	private String path;
	private List<LogHeader> headers;
	private Object body;

	public LogHttpResponseEntity() {
	}

	public LogHttpResponseEntity(String type, String method, String path, List<LogHeader> headers, Object body) {
		this.type = type;
		this.method = method;
		this.path = path;
		this.headers = headers;
		this.body = body;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<LogHeader> getHeaders() {
		return headers;
	}

	public void setHeaders(List<LogHeader> headers) {
		this.headers = headers;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}
}

package mutu.sprest.logging.dto;

public class LogMessage {
	private String process;
	private Object payload;

	public LogMessage() {
	}

	public LogMessage(String process, Object payload) {
		super();
		this.process = process;
		this.payload = payload;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public Object getPayload() {
		return payload;
	}

	public void setPayload(Object payload) {
		this.payload = payload;
	}

}

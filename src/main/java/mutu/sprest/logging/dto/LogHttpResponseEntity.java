package mutu.sprest.logging.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import okhttp3.Headers;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogHttpResponseEntity {
	private String type;
	private String method;
	private String path;
	private List<LogHeader> headers;
	private Object body;
	
	public void setHeadersMap(Headers headerMap) {
		if(headerMap == null) {
			return;
		}
		headers = new ArrayList<LogHeader>();
		LogHeader logHeader = new LogHeader();
		String key = null;
		for (int i = 0; i < headerMap.size(); i++) {
			Iterator<?> keys = headerMap.names().iterator();
			while (keys.hasNext()) {
				key = (String) keys.next();
				logHeader.setKey(key);
				logHeader.setValue(headerMap.get(key));
			}
		}
		headers.add(logHeader);
	}
}

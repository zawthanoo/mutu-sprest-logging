package mutu.sprest.logging.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogHttpRequestEntity {
	private String type;
	private String method;
	private String path;
	private List<LogHeader> headers;
	private Object body;
	
	public void setHeadersMap(Map<String, String> headerMap) {
		if(headerMap == null) {
			return;
		}
		headers = new ArrayList<LogHeader>();
		LogHeader logHeader = new LogHeader();
		String key = null;
		for (int i = 0; i < headerMap.size(); i++) {
			Iterator<?> keys = headerMap.keySet().iterator();
			while (keys.hasNext()) {
				key = (String) keys.next();
				logHeader.setKey(key);
				logHeader.setValue(headerMap.get(key));
			}
		}
		headers.add(logHeader);
	}
}

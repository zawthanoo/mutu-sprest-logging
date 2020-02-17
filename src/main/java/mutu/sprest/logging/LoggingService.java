package mutu.sprest.logging;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.ObjectMessage;
import org.springframework.stereotype.Component;

import mutu.sprest.logging.dto.LogHeader;
import mutu.sprest.logging.dto.LogHttpRequestEntity;
import mutu.sprest.logging.dto.LogHttpResponseEntity;
import mutu.sprest.logging.dto.LogMessage;
/**
 * @author Zaw Than Oo
 * @since 01-DEC-2018 <br/>
 *        This LoggingService to log and track for http request and response.
 *        ObjectMessage is used to support for log4j jsonlogger appender.
 */
@Component
public class LoggingService {
	private static final Logger logger = LogManager.getLogger(LoggingService.class);

	public void logRequest(HttpServletRequest httpServletRequest, Object body) {
		List<LogHeader> headers = buildHeadersMap(httpServletRequest);
		LogHttpRequestEntity entity = new LogHttpRequestEntity();
		entity.setType("http-request");
		entity.setMethod(httpServletRequest.getMethod());
		entity.setHeaders(headers);
		entity.setPath(httpServletRequest.getRequestURI());
		entity.setBody(body);
		logger.debug(new ObjectMessage(new LogMessage("Request", entity)));
	}

	public void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object body) {
		List<LogHeader> headers = buildHeadersMap(httpServletResponse);
		LogHttpResponseEntity entity = new LogHttpResponseEntity();
		entity.setType("http-response");
		entity.setMethod(httpServletRequest.getMethod());
		entity.setHeaders(headers);
		entity.setPath(httpServletRequest.getRequestURI());
		entity.setBody(body);
		logger.debug(new ObjectMessage(new LogMessage("Response", entity)));
	}

	private List<LogHeader> buildHeadersMap(HttpServletRequest request) {
		List<LogHeader> list = new ArrayList<LogHeader>();
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			list.add(new LogHeader(key, value));
		}
		return list;
	}
	
	private List<LogHeader> buildHeadersMap(HttpServletResponse response) {
		List<LogHeader> list = new ArrayList<LogHeader>();
		Collection<String> headerNames = response.getHeaderNames();
		for (String header : headerNames) {
			list.add(new LogHeader(header, response.getHeader(header)));
		}
		return list;
	}
}
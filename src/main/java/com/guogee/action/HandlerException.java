package com.guogee.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class HandlerException extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}

	private Exception exception;

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public String handler() throws Exception {
		Throwable t =this.exception;
//		JSONObject result = new JSONObject();
//		result.put("result", false);
//		result.put("erro", getErrorHtml(ServletActionContext.getRequest(),t));
		inputStream = new ByteArrayInputStream(
				(getErrorHtml(ServletActionContext.getRequest(),t)).getBytes("utf-8"));
		return SUCCESS;
	}

	/**
	 * 将异常信息转化成字符串
	 * 
	 * @param t
	 * @return
	 * @throws IOException
	 */
	private static String _Exception(Throwable t) throws IOException {
		if (t == null)
			return null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			t.printStackTrace(new PrintStream(baos));
		} finally {
			baos.close();
		}
		return baos.toString();
	}
	
	public static String getErrorHtml(HttpServletRequest req, Throwable t) {
	    StringBuilder html = new StringBuilder();
	    if(req != null){
	        html.append("<h2>Request Headers</h2><table>");   
	        html.append("<tr><th>Request URL</th><td>");
	        html.append(req.getRequestURL().toString());
	        if(req.getQueryString()!=null){
	            html.append('?');
	            html.append(req.getQueryString());                      
	        }
	        html.append("</td></tr>");
	        html.append("<tr><th>Remote Addr</th><td>");
	        html.append(req.getRemoteHost());
	        html.append("</td></tr>");
	        html.append("<tr><th>Request Method</th><td>");
	        html.append(req.getMethod());
	        html.append("</td></tr>");
	        html.append("<tr><th>CharacterEncoding</th><td>");
	        html.append(req.getCharacterEncoding());
	        html.append("</td></tr>");
	        html.append("<tr><th>Request Locale</th><td>");
	        html.append(req.getLocale());
	        html.append("</td></tr>");
	        html.append("<tr><th>Content Type</th><td>");
	        html.append(req.getContentType());
	        html.append("</td></tr>");
	        Enumeration headers = req.getHeaderNames();
	        while(headers.hasMoreElements()){
	            String key = (String)headers.nextElement();
	            html.append("<tr><th>");
	            html.append(key);
	            html.append("</th><td>");
	            html.append(req.getHeader(key));
	            html.append("</td></tr>");
	        }       
	        html.append("</table>");
	    }
	    html.append("<h2>");
	    html.append(t.getClass().getName());
	    html.append('(');
	    html.append(DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"));
	    html.append(")</h2><pre>");
	    try {
	        html.append(_Exception(t));
	    } catch (IOException ex) {}
	    html.append("</pre>");
	    html.append("<h2>System Properties</h2><table>");     
	    Set props = System.getProperties().keySet();
	    for(Object prop : props){
	        html.append("<tr><th>");
	        html.append(prop);
	        html.append("</th><td>");
	        html.append(System.getProperty((String)prop));
	        html.append("</td></tr>");
	    }
	    html.append("</table>");
	    return html.toString();
	}

}

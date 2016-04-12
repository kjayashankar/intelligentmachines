package client.controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//import jay.iot.commons.Constants;

public class Connection{

	private HttpClient client;
	private String endPointURL;
	HttpUriRequest requestType = new HttpPost();
	
	
	public Connection() {
		super();
	}

	@SuppressWarnings("deprecation")
	public void acquire() {
		client = new DefaultHttpClient();
	}

	public void release() {
		client = null;
	}

	public void setURL(String url) {
		endPointURL = url;
	}

	public void setRequestType(String req) {
		requestType = getRequestType(req);
	}

	private HttpUriRequest getRequestType(String req) {
		
		if("GET".equalsIgnoreCase(req))
			return new HttpGet(endPointURL);
		else if("POST".equalsIgnoreCase(req))
			return new HttpPost(endPointURL);
		else if("PUT".equalsIgnoreCase(req))
			return new HttpPut(endPointURL);
		else if("DELETE".equalsIgnoreCase(req))
			return new HttpDelete(endPointURL);
		return null;
	}

	public void setHeaders(List<Header> headers) {
		for(Header header : headers) {
			requestType.addHeader(header);
		}
		
		
	}

	public void setBody(HashMap<String, String> connBodyMap) {
		try{
			String outPut = convertMapToJson(connBodyMap);
			if("post".equalsIgnoreCase(requestType.getMethod()))
			((HttpPost)requestType).setEntity(new StringEntity(outPut));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	private String convertMapToJson(HashMap<String, String> connBodyMap) throws JsonProcessingException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		String outPut = objectMapper.writeValueAsString(connBodyMap);

		return outPut;
		
	}

	public String connect() throws ParseException, IOException {
	
		HttpResponse response = null;
		requestType.removeHeaders("Content-Type");
		requestType.addHeader("Content-Type", "application/json");
		try{
			response = client.execute(requestType);
		}
		
		catch(Exception e) {
			e.printStackTrace();
			try {
				response.setEntity(new StringEntity("Error"));
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		}
		finally{
			release();
		}
		
		return EntityUtils.toString(response.getEntity());
	}

	
}

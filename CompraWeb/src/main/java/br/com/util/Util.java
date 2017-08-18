package br.com.util;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

public class Util {
	
	public static ResponseBuilder msgRequest(String msg){
		Map<String, String> responseObj = new HashMap<>();
		responseObj.put("error", msg);
		return Response.status(Response.Status.EXPECTATION_FAILED).entity(responseObj);
	}

}

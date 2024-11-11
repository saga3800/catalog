package com.acn.springboot.catalog.app.middleware;

import org.springframework.stereotype.Service;

@Service
public class Middleware implements IMiddleware{

	public boolean validateToken(String token) {
		
	    String acceso = "5eb40c90f1cd0cbeb4b03811";

		if(acceso.equals(token)) {
			return true;
		}else {
		return false;
		}
	}
}

package com.acn.springboot.catalog.app.middleware;

public interface IMiddleware {

	public boolean validateToken(String token);
}

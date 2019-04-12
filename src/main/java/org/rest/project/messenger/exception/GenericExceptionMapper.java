package org.rest.project.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.rest.project.messenger.model.ErrorMessage;

//@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable ex) {
		// TODO Auto-generated method stub
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 500, "www.google.com"); 
		return Response
				.status(Status.INTERNAL_SERVER_ERROR)
//				.type(MediaType.APPLICATION_JSON)
				.entity(errorMessage)
				.build();
	}
	
	
}

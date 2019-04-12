package org.rest.project.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.rest.project.messenger.model.ErrorMessage;

@Provider
public class DataNotCreatedExceptionMapper implements ExceptionMapper<DataNotCreatedException> {

	@Override
	public Response toResponse(DataNotCreatedException ex) {
		// TODO Auto-generated method stub
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 404, "www.google.com"); 
		return Response
				.status(Status.NOT_FOUND)
//				.type(MediaType.APPLICATION_JSON)
				.entity(errorMessage)
				.build();
	}
	
	
}

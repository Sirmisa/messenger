package org.rest.project.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.rest.project.messenger.database.DatabaseClass;
import org.rest.project.messenger.model.Comment;
import org.rest.project.messenger.model.ErrorMessage;
import org.rest.project.messenger.model.Message;

public class CommentService {
	
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public List<Comment> getAllComments(long messageId) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return new ArrayList<Comment>(comments.values());
	}
	
	public Comment getComment(long messageId, long commentId) {
		
		Response response = dataNotFoundErrorMessage();
		Message message = messages.get(messageId);
		if(message == null) {
			throw new WebApplicationException(response);
		}
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		
		Comment comment =  comments.get(commentId);
		if(comment == null) {
			throw new WebApplicationException(response);
		}
		
		return comment;
	}
	
	public Comment addComment(long messageId, Comment comment) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		comment.setId(comments.size() + 1);
		comments.put(comment.getId(), comment);
		
		Response response = dataNotCreatedErrorMessage();
		if(messages.get(messageId).getComments() == null) {
			throw new WebApplicationException(response);
		}
		return comment;
	}
	
	public Comment updateComment(long messageId, Comment comment) {
		Response response = dataNotFoundErrorMessage();
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		if (comment.getId() <= 0) {
			return null;
		}
		if(comments == null) {
			throw new WebApplicationException(response);
		}
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment removeComment(long messageId, long commentId) {
		Response response = dataNotFoundErrorMessage();
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		if(comments == null) {
			throw new WebApplicationException(response);
		}
		return comments.remove(commentId);
	}
	
	private Response dataNotFoundErrorMessage() {
		ErrorMessage errorMessage = new ErrorMessage("Comment not found", 404, "api.messenger.com"); 
		Response response = Response
				.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
		return response;
	}
	
	private Response dataNotCreatedErrorMessage() {
		ErrorMessage errorMessage = new ErrorMessage("Comment not found", 404, "api.messenger.com"); 
		Response response = Response
				.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
		return response;
	}
}

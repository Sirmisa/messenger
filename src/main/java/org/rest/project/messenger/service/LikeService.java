package org.rest.project.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.rest.project.messenger.database.DatabaseClass;
import org.rest.project.messenger.model.ErrorMessage;
import org.rest.project.messenger.model.Like;
import org.rest.project.messenger.model.Message;

public class LikeService {
	
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public List<Like> getAllLikes(long messageId) {
		Map<Long, Like> likes = messages.get(messageId).getLikes();
		return new ArrayList<Like>(likes.values());
	}
	
	public Like getLike(long messageId, long likeId) {
		
		Response response = dataNotFoundErrorMessage();
		Message message = messages.get(messageId);
		if(message == null) {
			throw new WebApplicationException(response);
		}
		Map<Long, Like> likes = messages.get(messageId).getLikes();
		
		Like like =  likes.get(likeId);
		if(like == null) {
			throw new WebApplicationException(response);
		}
		
		return like;
	}
	
	public Like addLike(long messageId, Like like) {
		Map<Long, Like> likes = messages.get(messageId).getLikes();
		like.setId(likes.size() + 1);
		likes.put(like.getId(), like);
		
		Response response = dataNotCreatedErrorMessage();
		if(messages.get(messageId).getLikes() == null) {
			throw new WebApplicationException(response);
		}
		return like;
	}
	
	public Like updateLike(long messageId, Like like) {
		Response response = dataNotFoundErrorMessage();
		Map<Long, Like> likes = messages.get(messageId).getLikes();
		if (like.getId() <= 0) {
			return null;
		}
		if(likes == null) {
			throw new WebApplicationException(response);
		}
		likes.put(like.getId(), like);
		return like;
	}
	
	public Like removeLike(long messageId, long likeId) {
		Response response = dataNotFoundErrorMessage();
		Map<Long, Like> likes = messages.get(messageId).getLikes();
		if(likes == null) {
			throw new WebApplicationException(response);
		}
		return likes.remove(likeId);
	}
	
	private Response dataNotFoundErrorMessage() {
		ErrorMessage errorMessage = new ErrorMessage("Like not found", 404, "api.messenger.com"); 
		Response response = Response
				.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
		return response;
	}
	
	private Response dataNotCreatedErrorMessage() {
		ErrorMessage errorMessage = new ErrorMessage("Like not found", 404, "api.messenger.com"); 
		Response response = Response
				.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
		return response;
	}
}

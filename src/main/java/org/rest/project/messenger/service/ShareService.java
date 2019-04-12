package org.rest.project.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.rest.project.messenger.database.DatabaseClass;
import org.rest.project.messenger.model.ErrorMessage;
import org.rest.project.messenger.model.Message;
import org.rest.project.messenger.model.Share;

public class ShareService {
	
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public List<Share> getAllShares(long messageId) {
		Map<Long, Share> shares = messages.get(messageId).getShares();
		return new ArrayList<Share>(shares.values());
	}
	
	public Share getShare(long messageId, long shareId) {
		
		Response response = dataNotFoundErrorMessage();
		Message message = messages.get(messageId);
		if(message == null) {
			throw new WebApplicationException(response);
		}
		Map<Long, Share> shares = messages.get(messageId).getShares();
		
		Share share =  shares.get(shareId);
		if(share == null) {
			throw new WebApplicationException(response);
		}
		
		return share;
	}
	
	public Share addShare(long messageId, Share share) {
		Map<Long, Share> shares = messages.get(messageId).getShares();
		share.setId(shares.size() + 1);
		shares.put(share.getId(), share);
		
		Response response = dataNotCreatedErrorMessage();
		if(messages.get(messageId).getShares() == null) {
			throw new WebApplicationException(response);
		}
		return share;
	}
	
	public Share updateShare(long messageId, Share share) {
		Response response = dataNotFoundErrorMessage();
		Map<Long, Share> shares = messages.get(messageId).getShares();
		if (share.getId() <= 0) {
			return null;
		}
		if(shares == null) {
			throw new WebApplicationException(response);
		}
		shares.put(share.getId(), share);
		return share;
	}
	
	public Share removeShare(long messageId, long shareId) {
		Response response = dataNotFoundErrorMessage();
		Map<Long, Share> shares = messages.get(messageId).getShares();
		if(shares == null) {
			throw new WebApplicationException(response);
		}
		return shares.remove(shareId);
	}
	
	private Response dataNotFoundErrorMessage() {
		ErrorMessage errorMessage = new ErrorMessage("Share not found", 404, "api.messenger.com"); 
		Response response = Response
				.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
		return response;
	}
	
	private Response dataNotCreatedErrorMessage() {
		ErrorMessage errorMessage = new ErrorMessage("Share not found", 404, "api.messenger.com"); 
		Response response = Response
				.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
		return response;
	}
}

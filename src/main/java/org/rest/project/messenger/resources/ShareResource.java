package org.rest.project.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.rest.project.messenger.model.Share;
import org.rest.project.messenger.service.ShareService;

@Path("sharesdata")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class ShareResource {

	private ShareService shareService = new ShareService();
	
	@GET
	public List<Share> getAllShares(@PathParam("messageId") long messageId) {
		return shareService.getAllShares(messageId);
	}
	
	@POST
	public Share addShare(@PathParam("messageId") long messageId, Share share) {
		return shareService.addShare(messageId, share);
	}
	
	@PUT
	@Path("/{shareId}")
	public Share updateShare(@PathParam("messageId") long messageId, @PathParam("shareId") long id, Share share) {
		share.setId(id);
		return shareService.updateShare(messageId, share);
	}
	
	@DELETE
	@Path("/{shareId}")
	public void deleteShare(@PathParam("messageId") long messageId, @PathParam("shareId") long shareId) {
		shareService.removeShare(messageId, shareId);
	}
	
	
	@GET
	@Path("/{shareId}")
	public Share getMessage(@PathParam("messageId") long messageId, @PathParam("shareId") long shareId) {
		return shareService.getShare(messageId, shareId);
	}
	
}

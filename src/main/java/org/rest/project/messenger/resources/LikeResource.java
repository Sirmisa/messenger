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

import org.rest.project.messenger.model.Like;
import org.rest.project.messenger.service.LikeService;
@Path("likesdata")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class LikeResource {

	private LikeService likeService = new LikeService();
	
	@GET
	public List<Like> getAllLikes(@PathParam("messageId") long messageId) {
		return likeService.getAllLikes(messageId);
	}
	
	@POST
	public Like addLike(@PathParam("messageId") long messageId, Like like) {
		return likeService.addLike(messageId, like);
	}
	
	@PUT
	@Path("/{likeId}")
	public Like updateLike(@PathParam("messageId") long messageId, @PathParam("likeId") long id, Like like) {
		like.setId(id);
		return likeService.updateLike(messageId, like);
	}
	
	@DELETE
	@Path("/{likeId}")
	public void deleteLike(@PathParam("messageId") long messageId, @PathParam("likeId") long likeId) {
		likeService.removeLike(messageId, likeId);
	}
	
	
	@GET
	@Path("/{likeId}")
	public Like getMessage(@PathParam("messageId") long messageId, @PathParam("likeId") long likeId) {
		return likeService.getLike(messageId, likeId);
	}
	
}

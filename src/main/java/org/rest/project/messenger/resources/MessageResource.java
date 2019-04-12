package org.rest.project.messenger.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.rest.project.messenger.model.Message;
import org.rest.project.messenger.resources.beans.MessageFilterBean;
import org.rest.project.messenger.service.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
public class MessageResource {

	MessageService messageService = new MessageService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getJsonMessages(@BeanParam MessageFilterBean filterBean) {

		if (filterBean.getYear() > 0) {
			return messageService.getAllMessagesForYear(filterBean.getYear());
		}
		if (filterBean.getStart() >= 0 && filterBean.getSize() > 0) {
			return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}
		return messageService.getAllMessages();
	}
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Message> getXMLMessages(@BeanParam MessageFilterBean filterBean) {

		if (filterBean.getYear() > 0) {
			return messageService.getAllMessagesForYear(filterBean.getYear());
		}
		if (filterBean.getStart() >= 0 && filterBean.getSize() > 0) {
			return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}
		return messageService.getAllMessages();
	}

	@POST
	public Response addMessage(Message message, @Context UriInfo uriInfo) {

		Message newMessage = messageService.addMessage(message);
		String newId = String.valueOf(newMessage.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri).entity(newMessage).build();
	}

	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long id, Message message) {
		message.setId(id);
		return messageService.updateMessage(message);
	}

	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long id) {
		messageService.removeMessage(id);
	}

	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long id, @Context UriInfo uriInfo) {
		Message message = messageService.getMessage(id);

		message.addLinks(getUriForSelf(uriInfo, message), "self");
		message.addLinks(getUriForProfile(uriInfo, message), "profile");
		message.addLinks(getUriForComments(uriInfo, message), "comments");
		message.addLinks(getUriForShares(uriInfo, message), "shares");
		message.addLinks(getUriForLikes(uriInfo, message), "likes");
		
		return message;
	}
	
	private String getUriForLikes(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(MessageResource.class, "getLikeResource")
//				.path(LikeResource.class)
				.resolveTemplate("messageId", message.getId())
				.build()
				.toString();
				return uri;
	}

	private String getUriForShares(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(MessageResource.class, "getShareResource")
//				.path(LikeResource.class)
				.resolveTemplate("messageId", message.getId())
				.build()
				.toString();
				return uri;
	}
	
	private String getUriForComments(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(MessageResource.class, "getCommentResource")
//				.path(CommentResource.class)
				.resolveTemplate("messageId", message.getId())
				.build()
				.toString();
				return uri;
	}
	
	private String getUriForProfile(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()
				.path(ProfileResource.class)// http://localhost:8080/messenger/webapi/
				.path(message.getAuthor())//  /profiles
				.build()//  /{authorName}
				.toString();
				return uri;
	}
	private String getUriForSelf(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()
		.path(MessageResource.class)// http://localhost:8080/messenger/webapi/
		.path(Long.toString(message.getId()))//  /messages
		.build()//  /{messageId}
		.toString();
		return uri;
	}

	@Path("/{messageId}/comments/")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}
	
	@Path("/{messageId}/shares/")
	public ShareResource getShareResource() {
		return new ShareResource();
	}
	
	@Path("/{messageId}/likes/")
	public LikeResource getLikeResource() {
		return new LikeResource();
	}

}
package org.rest.project.messenger.resources;

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
import javax.ws.rs.core.UriInfo;

import org.rest.project.messenger.model.Profile;
import org.rest.project.messenger.resources.beans.ProfileFilterBean;
import org.rest.project.messenger.service.ProfileService;

@Path("/profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {

	private ProfileService profileService = new ProfileService();
	
	@GET
	public List<Profile> getProfiles(@BeanParam ProfileFilterBean filterBean) {
		if (!filterBean.getfirstName().isEmpty()) {
			return profileService.getAllProfilesForFirstName(filterBean.getfirstName());
		}
		if (filterBean.getStart() >= 0 && filterBean.getSize() > 0) {
			return profileService.getAllProfilesPaginated(filterBean.getStart(), filterBean.getSize());
		}
		return profileService.getAllProfiles();
	}
	
	@POST
	public Profile addProfile(Profile profile) {
		return profileService.addProfile(profile);
	}
	
	@GET
	@Path("/{profileName}")
	public Profile getProfile(@PathParam("profileName") String profileName, @Context UriInfo uriInfo) {
		
		Profile profile = profileService.getProfile(profileName);
		profile.addLinks(getUriForSelf(uriInfo, profile), "self");
		return profile;
	}
	
	@PUT
	@Path("/{profileName}")
	public Profile updateProfile(@PathParam("profileName") String profileName, Profile profile) {
		profile.setProfileName(profileName);
		return profileService.updateProfile(profile);
	}
	
	@DELETE
	@Path("/{profileName}")
	public void deleteProfile(@PathParam("profileName") String profileName) {
		profileService.removeProfile(profileName);
	}
	
	private String getUriForSelf(UriInfo uriInfo, Profile profile) {
		String uri = uriInfo.getBaseUriBuilder()
		.path(ProfileResource.class)
		.path(profile.getProfileName())
		.build()
		.toString();
		return uri;
	}
	
}

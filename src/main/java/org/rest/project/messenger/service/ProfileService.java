package org.rest.project.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.rest.project.messenger.database.DatabaseClass;
import org.rest.project.messenger.exception.DataNotCreatedException;
import org.rest.project.messenger.exception.DataNotFoundException;
import org.rest.project.messenger.model.Profile;

public class ProfileService {

	private Map<String, Profile> profiles = DatabaseClass.getProfiles();
	
	public ProfileService() {
		profiles.put("koushik", new Profile(1L, "koushik", "Koushik", "Kothagal"));
	}
	
	public List<Profile> getAllProfiles() {
		return new ArrayList<Profile>(profiles.values()); 
	}
	
	public List<Profile> getAllProfilesForFirstName(String firstName) {
		List<Profile> profilesForFirstName = new ArrayList<>();
		
		for (Profile profile : profiles.values()) {
			
			if (profile.getFirstName().equalsIgnoreCase(firstName)) {
				profilesForFirstName.add(profile);
			}
		}
		return profilesForFirstName;
	}
	
	public List<Profile> getAllProfilesPaginated(int start, int size) {
		ArrayList<Profile> list = new ArrayList<Profile>(profiles.values());
		if (start + size > list.size()) return new ArrayList<Profile>();
		return list.subList(start, start + size); 
	}
	
	public Profile getProfile(String profileName) {
		
		Profile profile = profiles.get(profileName);
		 if(profile == null) {
			 throw new DataNotFoundException("Profile " + profileName + " not found");
		 }
		return profile;
	}
	
	public Profile addProfile(Profile profile) {
		profile.setId(profiles.size() + 1);
		profiles.put(profile.getProfileName(), profile);
		if(profiles.get(profile.getProfileName()) == null) {
			 throw new DataNotCreatedException("Profile could not be created");
		}
		return profile;
	}
	
	public Profile updateProfile(Profile profile) {
		if (profile.getProfileName().isEmpty()) {
			return null;
		}
		
		if(profiles.get(profile.getProfileName()) == null) {
			 throw new DataNotFoundException("Profile " + profile.getProfileName() + " not found");
		}
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile removeProfile(String profileName) {
		Profile profile = profiles.get(profileName);
		 if(profile == null) {
			 throw new DataNotFoundException("Profile " + profileName + " not found");
		 }
		return profiles.remove(profileName);
	}
	
	
}

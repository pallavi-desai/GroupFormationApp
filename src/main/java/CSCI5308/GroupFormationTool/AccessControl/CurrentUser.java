package CSCI5308.GroupFormationTool.AccessControl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import CSCI5308.GroupFormationTool.SystemConfig;

public class CurrentUser {
	private static final Logger log = LoggerFactory.getLogger(CurrentUser.class);
	private static CurrentUser uniqueInstance = null;

	private CurrentUser() {
	}

	public static CurrentUser instance() {
		if (null == uniqueInstance) {
			uniqueInstance = new CurrentUser();
		}
		return uniqueInstance;
	}

	public InterfaceUser getCurrentAuthenticatedUser() {
		IUserPersistence userDB = SystemConfig.instance().getUserDB();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.isAuthenticated()) {
			String bannerID = authentication.getPrincipal().toString();
			InterfaceUser u = UserObjectFactory.createObject(new UserFactory());
			userDB.loadUserByBannerID(bannerID, u);
			if (u.isValidUser()) {
				log.info("Valid user bannerID = {}", bannerID);
				return u;
			}
		}
		log.warn("InValid user bannerID");
		return null;
	}
}

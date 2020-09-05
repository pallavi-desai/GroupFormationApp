package CSCI5308.GroupFormationTool.Security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2LoginConfigurer.UserInfoEndpointConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.InterfaceUser;
import CSCI5308.GroupFormationTool.AccessControl.User;

public class CustomAuthenticationManager implements AuthenticationManager {
	private static final Logger log = LoggerFactory.getLogger(CustomAuthenticationManager.class);

	private static final String CUSTOME_AUTH_MANAGER_LOG = "AuthenticationManager";
	private static final String ADMIN_BANNER_ID = "B-000000";

	private Authentication checkAdmin(String password, InterfaceUser u, Authentication authentication)
			throws AuthenticationException {
		ISecurityFactory secFactory = SystemConfig.instance().getSecurityFactory();
		if (password.equals(u.getPassword())) {
			log.warn("user={}, action={}, status={}", u.getBanner(), "Check Admin User Credentials", "Success");
			List<GrantedAuthority> rights = new ArrayList<GrantedAuthority>();
			rights.add(secFactory.createGrantedAuthority("ADMIN"));
			AbstractAuthenticationToken token = secFactory.createAuthenticationToken(authentication.getPrincipal(),
					authentication.getCredentials(), rights);
			log.info("user={}, action={}, status={}", u.getBanner(), "Create Admin Authentication Token", "Success");
			return token;
		} else {
			log.warn("user={}, action={}, status={}", u.getBanner(), "Check Admin User Credentials", "Fail");
			throw new BadCredentialsException("1000");
		}
	}

	private Authentication checkNormal(String password, InterfaceUser u, Authentication authentication)
			throws AuthenticationException {
		ISecurityFactory secFactory = SystemConfig.instance().getSecurityFactory();
		IPasswordEncryption passwordEncryption = secFactory.createPassworEncryption();

		if (passwordEncryption.matches(password, u.getPassword())) {
			log.warn("user={}, action={}, status={}", u.getBanner(), "Check User Credentials", "Success");
			List<GrantedAuthority> rights = new ArrayList<GrantedAuthority>();
			rights.add(secFactory.createGrantedAuthority("USER"));
			AbstractAuthenticationToken token = secFactory.createAuthenticationToken(authentication.getPrincipal(),
					authentication.getCredentials(), rights);
			log.info("user={}, action={}, status={}", u.getBanner(), "Create User Authentication Token", "Success");
			return token;
		} else {
			log.warn("user={}, action={}, status={}", u.getBanner(), "Check User Credentials", "Fail");
			throw new BadCredentialsException("1000");
		}
	}

	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String bannerID = authentication.getPrincipal().toString();
		String password = authentication.getCredentials().toString();
		IUserPersistence userDB = SystemConfig.instance().getUserDB();
		InterfaceUser u;

		try {
			u = SystemConfig.instance().getSecurityFactory().createUser(bannerID, userDB);
		} catch (Exception e) {
			log.warn("user={}, action={}, status={}, message={}", CUSTOME_AUTH_MANAGER_LOG, "User Authentication",
					"Fail", e.getMessage());
			throw new AuthenticationServiceException("1000");
		}
		log.warn("user={}, action={}, status={}", u.getBanner(), "User Authentication", "Starting...");
		if (u.isValidUser()) {
			log.warn("user={}, action={}, status={}", u.getBanner(), "Check User Validity", "Success");
			if (bannerID.toUpperCase().equals(ADMIN_BANNER_ID)) {
				return checkAdmin(password, u, authentication);
			} else {
				return checkNormal(password, u, authentication);
			}
		} else {
			log.warn("user={}, action={}, status={}", u.getBanner(), "Check User Validity", "Fail");
			throw new BadCredentialsException("1001");
		}
	}
}

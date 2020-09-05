package CSCI5308.GroupFormationTool.Security;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.InterfaceUser;
import CSCI5308.GroupFormationTool.AccessControl.UserObjectFactory;

public interface ISecurityFactory {
	public IPasswordEncryption createPassworEncryption();

	public AbstractAuthenticationToken createAuthenticationToken(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities);

	public GrantedAuthority createGrantedAuthority(String role);
	
	public InterfaceUser createUser(String bannerID, IUserPersistence userDB);
}

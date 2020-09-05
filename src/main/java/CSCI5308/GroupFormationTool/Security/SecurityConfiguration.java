package CSCI5308.GroupFormationTool.Security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	private static final Logger log = LoggerFactory.getLogger(SecurityConfiguration.class);
	private static final String SECURITY_CONFIG_LOG = "SecurityConfiguration";

	@Override
	public void configure(WebSecurity web) throws Exception {
		log.info("user={}, action={}", SECURITY_CONFIG_LOG, "Resource Authorization");
		web.ignoring().antMatchers("/resources/**");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		log.info("user={}, action={}", SECURITY_CONFIG_LOG, "Resource Authorization");
		http.authorizeRequests().antMatchers("/public/**", "/**").permitAll().antMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().and().logout()
				.permitAll();
	}

	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return new CustomAuthenticationManager();
	}
}

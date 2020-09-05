package CSCI5308.GroupFormationTool.Security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.InterfaceUser;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.AccessControl.UserFactory;
import CSCI5308.GroupFormationTool.AccessControl.UserObjectFactory;
import CSCI5308.GroupFormationTool.PasswordValidationPolicy.IPasswordValidationFactory;
import CSCI5308.GroupFormationTool.PasswordValidationPolicy.IPasswordValidationManager;

@Controller
public class SignupController {
	private static final Logger log = LoggerFactory.getLogger(SignupController.class);
	private static final String SIGNUP_LOG = "SignUp";

	private final String USERNAME = "username";
	private final String PASSWORD = "password";
	private final String PASSWORD_CONFIRMATION = "passwordConfirmation";
	private final String FIRST_NAME = "firstName";
	private final String LAST_NAME = "lastName";
	private final String EMAIL = "email";

	@GetMapping("/signup")
	public String displaySignup(Model model) {
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView processSignup(@RequestParam(name = USERNAME) String bannerID,
			@RequestParam(name = PASSWORD) String password,
			@RequestParam(name = PASSWORD_CONFIRMATION) String passwordConfirm,
			@RequestParam(name = FIRST_NAME) String firstName, @RequestParam(name = LAST_NAME) String lastName,
			@RequestParam(name = EMAIL) String email) {
		boolean success = false;
		IPasswordValidationFactory pvFactory = SystemConfig.instance().getPasswordValidationFactory();
		IPasswordValidationManager passwordValidationManager = pvFactory.createPasswordValidationManager();
		List<String> failureMessages = new ArrayList<>();

		if (User.isBannerIDValid(bannerID) && User.isEmailValid(email) && User.isFirstNameValid(firstName)
				&& User.isLastNameValid(lastName) && password.equals(passwordConfirm)) {
			if (passwordValidationManager.isValidPassword(password)) {
				InterfaceUser u = UserObjectFactory.createObject(new UserFactory());
				u.setBannerID(bannerID);
				u.setPassword(password);
				u.setFirstName(firstName);
				u.setLastName(lastName);
				u.setEmail(email);
				IUserPersistence userDB = SystemConfig.instance().getUserDB();
				IPasswordEncryption passwordEncryption = SystemConfig.instance().getSecurityFactory()
						.createPassworEncryption();
				success = u.createUser(userDB, passwordEncryption, null);
			} else {
				failureMessages.addAll(passwordValidationManager.getPasswordValidationFailures(password));
			}
		}
		ModelAndView m;
		if (success) {
			log.warn("user={}, action={}, status={}", SIGNUP_LOG, "Sign Up", "Success");
			m = new ModelAndView("login");
		} else {
			log.warn("user={}, action={}, status={}", SIGNUP_LOG, "Sign Up", "Fail");
			m = new ModelAndView("signup");
			failureMessages.add("Invalid data, please check your values.");
			m.addObject("errorMessages", failureMessages);
		}
		return m;
	}
}
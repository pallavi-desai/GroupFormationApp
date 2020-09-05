package CSCI5308.GroupFormationTool.Courses;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.SurveyResponses.ISurveyResponseDB;
import CSCI5308.GroupFormationTool.SurveyResponses.SurveyResponseDBFactory;

@Controller
public class CourseController {
	private static final Logger log = LoggerFactory.getLogger(CourseController.class);
	private static final String ID = "id";

	@GetMapping("/course/course")
	public String course(Model model, @RequestParam(name = ID) long courseID,
			@RequestParam(name = "userID") long userID) {
		ICoursePersistence courseDB = SystemConfig.instance().getCourseDB();
		ISurveyResponseDB surveyResponseDB = SurveyResponseDBFactory.FactorySingleton().createSurveyResponseDB();
		InterfaceCourse interfaceCourse = ObjectFactory.createObject(new CourseFactory());
		courseDB.loadCourseByID(courseID, interfaceCourse);
		model.addAttribute("course", interfaceCourse);
		model.addAttribute("userID", userID);
		List<Role> userRoles = interfaceCourse.getAllRolesForCurrentUserInCourse();
		if (userRoles.contains(Role.STUDENT)) {
			if (interfaceCourse.isCurrentUserEnrolledAsRoleInCourse("Student")) {
				model.addAttribute("enrolled", true);
				HashMap<Integer, Integer> surveyAvailable = surveyResponseDB.checkIfSurveyPublished(courseID);
				if (surveyAvailable.isEmpty()) {
					model.addAttribute("published", false);
				} else {
					for (Map.Entry<Integer, Integer> entry : surveyAvailable.entrySet()) {
						int surveyID = entry.getKey();
						int published = entry.getValue();
						if (published == 1) {
							model.addAttribute("surveyID", surveyID);
							model.addAttribute("published", true);
						} else {
							model.addAttribute("published", false);
						}
					}
				}
			}
		}
		if (null == userRoles) {
			// Default user is a guest.
			model.addAttribute("instructor", false);
			model.addAttribute("ta", false);
			model.addAttribute("student", false);
			model.addAttribute("guest", true);
			log.warn("User has no roles for courses: GUEST ROLE");
		} else {
			model.addAttribute("instructor", userRoles.contains(Role.INSTRUCTOR));
			model.addAttribute("ta", userRoles.contains(Role.TA));
			model.addAttribute("student", userRoles.contains(Role.STUDENT));
			model.addAttribute("guest", userRoles.isEmpty());
			log.info("User roles in the courses = {}", userRoles);
		}
		return "course/course";
	}

}

package CSCI5308.GroupFormationTool.Courses;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.AccessControl.InterfaceUser;
import CSCI5308.GroupFormationTool.AccessControl.UserFactory;
import CSCI5308.GroupFormationTool.AccessControl.UserObjectFactory;
import CSCI5308.GroupFormationTool.QuestionManager.IQuestionsPersistence;
import CSCI5308.GroupFormationTool.QuestionManager.InterfaceQuestionModel;
import CSCI5308.GroupFormationTool.QuestionManager.QManagerDbFactory;

@Controller
public class InstructorAdminController {
	private static final String ID = "id";
	private static final String FILE = "file";
	private static final String SUCCESSFUL = "successful";
	private static final String FAILURES = "failures";
	private static final String DISPLAY_RESULTS = "displayresults";

	@GetMapping("/course/instructoradmin")
	public String instructorAdmin(Model model, @RequestParam(name = ID) long courseID) {
		ICoursePersistence courseDB = SystemConfig.instance().getCourseDB();
		InterfaceCourse interfaceCourse = ObjectFactory.createObject(new CourseFactory());
		courseDB.loadCourseByID(courseID, interfaceCourse);
		model.addAttribute("course", interfaceCourse);
		model.addAttribute("displayresults", false);
		if (interfaceCourse.isCurrentUserEnrolledAsRoleInCourse(Role.INSTRUCTOR)
				|| interfaceCourse.isCurrentUserEnrolledAsRoleInCourse(Role.TA)) {
			return "course/instructoradmin";
		} else {
			return "logout";
		}
	}

	@GetMapping("/course/makegroups")
	public String assignGroups(Model model, @RequestParam(name = ID) long courseID) {

		IQuestionsPersistence questionDB = QManagerDbFactory.FactorySingleton().createQuestionDB();
		List<InterfaceQuestionModel> questions=questionDB.loadAllQuestionsBycourseID(String.valueOf(courseID));
		model.addAttribute("questions",questions );
		model.addAttribute("courseid", courseID);
		return "QuestionManager/makesurvey";
	}

	@GetMapping("/course/instructoradminresults")
	public String instructorAdmin(Model model, @RequestParam(name = ID) long courseID,
			@RequestParam(name = SUCCESSFUL, required = false) List<String> successful,
			@RequestParam(name = FAILURES, required = false) List<String> failures,
			@RequestParam(name = DISPLAY_RESULTS) boolean displayResults) {
		ICoursePersistence courseDB = SystemConfig.instance().getCourseDB();
		InterfaceCourse interfaceCourse = ObjectFactory.createObject(new CourseFactory());
		courseDB.loadCourseByID(courseID, interfaceCourse);
		model.addAttribute("course", interfaceCourse);
		model.addAttribute("displayresults", false);
		model.addAttribute(SUCCESSFUL, successful);
		model.addAttribute(FAILURES, failures);
		model.addAttribute(DISPLAY_RESULTS, displayResults);
		if (interfaceCourse.isCurrentUserEnrolledAsRoleInCourse(Role.INSTRUCTOR)
				|| interfaceCourse.isCurrentUserEnrolledAsRoleInCourse(Role.TA)) {
			return "course/instructoradmin";
		} else {
			return "logout";
		}
	}

	@GetMapping("/course/enrollta")
	public String enrollTA(Model model, @RequestParam(name = ID) long courseID) {
		ICoursePersistence courseDB = SystemConfig.instance().getCourseDB();
		InterfaceCourse interfaceCourse = ObjectFactory.createObject(new CourseFactory());
		courseDB.loadCourseByID(courseID, interfaceCourse);
		model.addAttribute("course", interfaceCourse);
		if (interfaceCourse.isCurrentUserEnrolledAsRoleInCourse(Role.INSTRUCTOR)
				|| interfaceCourse.isCurrentUserEnrolledAsRoleInCourse(Role.TA)) {
			ICourseUserRelationshipPersistence courseUserRelationshipDB = SystemConfig.instance()
					.getCourseUserRelationshipDB();
			List<InterfaceUser> allUsersNotCurrentlyTA = courseUserRelationshipDB.findAllUsersWithoutCourseRole(Role.TA,
					courseID);
			model.addAttribute("users", allUsersNotCurrentlyTA);
			return "course/enrollta";
		} else {
			return "logout";
		}
	}

	@RequestMapping(value = "/course/uploadcsv", consumes = { "multipart/form-data" })
	public ModelAndView upload(@RequestParam(name = FILE) MultipartFile file, @RequestParam(name = ID) long courseID) {
		ICoursePersistence courseDB = SystemConfig.instance().getCourseDB();
		InterfaceCourse interfaceCourse = ObjectFactory.createObject(new CourseFactory());
		courseDB.loadCourseByID(courseID, interfaceCourse);
		IStudentCSVParser parser = new StudentCSVParser(file);
		StudentCSVImport importer = new StudentCSVImport(parser, interfaceCourse);
		ModelAndView mav = new ModelAndView("redirect:/course/instructoradminresults?id=" + Long.toString(courseID));
		mav.addObject("successful", importer.getSuccessResults());
		mav.addObject("failures", importer.getFailureResults());
		mav.addObject("displayresults", true);
		return mav;
	}

	@RequestMapping(value = "/course/assignTAtocourse")
	public ModelAndView assignInstructorToCourse(@RequestParam(name = "ta") List<Integer> instructor,
			@RequestParam(name = ID) long courseID) {
		InterfaceCourse interfaceCourse = ObjectFactory.createObject(new CourseFactory());
		interfaceCourse.setId(courseID);
		Iterator<Integer> iter = instructor.iterator();
		ICourseUserRelationshipPersistence courseUserRelationshipDB = SystemConfig.instance()
				.getCourseUserRelationshipDB();
		while (iter.hasNext()) {
			InterfaceUser u = UserObjectFactory.createObject(new UserFactory());
			u.setId(iter.next().longValue());
			courseUserRelationshipDB.enrollUser(interfaceCourse, u, Role.TA);
		}
		ModelAndView mav = new ModelAndView("redirect:instructoradmin");
		mav.addObject("id", courseID);
		return mav;
	}

}
package CSCI5308.GroupFormationTool.CoursesTest;

import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Courses.CourseFactory;
import CSCI5308.GroupFormationTool.Courses.ICoursePersistence;
import CSCI5308.GroupFormationTool.Courses.InterfaceCourse;
import CSCI5308.GroupFormationTool.Courses.ObjectFactory;

public class CourseDBMock implements ICoursePersistence 
{
	public List<InterfaceCourse> loadAllCourses()
	{
		List<InterfaceCourse> courseList = new ArrayList<>();
		InterfaceCourse course = ObjectFactory.createObject(new CourseFactory());
		course.setId(0);
		course.setTitle("Software Engineering");
		courseList.add(course);
		course = new Course();
		course.setId(1);
		course.setTitle("Advanced Topics in Software Development");
		courseList.add(course);
		return courseList;
	}

	public void loadCourseByID(long id, InterfaceCourse course)
	{
		course.setId(id);
		course.setTitle("Software Engineering");
	}

	public boolean createCourse(InterfaceCourse course)
	{
		course.setId(0);
		course.setTitle("Software Engineering");
		return true;
	}

	public boolean deleteCourse(long id) 
	{
		InterfaceCourse course = ObjectFactory.createObject(new CourseFactory());
		course.setId(id);
		course.setTitle("Software Engineering");
		course.setDefaults();
		return true;
	}

}

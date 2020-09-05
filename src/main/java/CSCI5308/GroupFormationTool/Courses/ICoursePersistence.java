package CSCI5308.GroupFormationTool.Courses;

import java.util.List;

public interface ICoursePersistence {
	public List<InterfaceCourse> loadAllCourses();

	public void loadCourseByID(long id, InterfaceCourse course);

	public boolean createCourse(InterfaceCourse course);

	public boolean deleteCourse(long id);
}

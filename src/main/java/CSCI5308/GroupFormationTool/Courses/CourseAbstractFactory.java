package CSCI5308.GroupFormationTool.Courses;

public interface CourseAbstractFactory {

	public InterfaceCourse createCourseObject();
	
	public InterfaceCourse createCourseObject(long id, ICoursePersistence courseDB);
}

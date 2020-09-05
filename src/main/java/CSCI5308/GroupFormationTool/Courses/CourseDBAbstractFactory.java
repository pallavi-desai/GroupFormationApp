package CSCI5308.GroupFormationTool.Courses;

public interface CourseDBAbstractFactory {
	public ICoursePersistence createCourseDBObject();
}

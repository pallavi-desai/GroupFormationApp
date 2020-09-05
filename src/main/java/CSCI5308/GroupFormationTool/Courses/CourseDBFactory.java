package CSCI5308.GroupFormationTool.Courses;

public class CourseDBFactory implements CourseDBAbstractFactory {

	@Override
	public ICoursePersistence createCourseDBObject() {
		return new CourseDB();
	}

}

package CSCI5308.GroupFormationTool.Courses;

public class CourseFactory implements CourseAbstractFactory {

	public InterfaceCourse createCourseObject() {
		return new Course();
	}
	
	public InterfaceCourse createCourseObject(long id, ICoursePersistence courseDB) {
		return new Course(id,courseDB);
	}

}

package CSCI5308.GroupFormationTool.Courses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

public class CourseDB implements ICoursePersistence {
	private static final Logger log = LoggerFactory.getLogger(CourseDB.class);

	public List<InterfaceCourse> loadAllCourses() {
		List<InterfaceCourse> courses = new ArrayList<InterfaceCourse>();
		CallStoredProcedure proc = null;
		try {
			proc = new CallStoredProcedure("spLoadAllCourses()");
			ResultSet results = proc.executeWithResults();
			if (null != results) {
				while (results.next()) {
					long id = results.getLong(1);
					String title = results.getString(2);
					InterfaceCourse interfaceCourse = ObjectFactory.createObject(new CourseFactory());
					interfaceCourse.setId(id);
					interfaceCourse.setTitle(title);
					courses.add(interfaceCourse);
				}
			}
		} catch (SQLException e) {
			log.error("SQL Exception= {} ", e.getMessage());
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return courses;
	}

	public void loadCourseByID(long id, InterfaceCourse course) {
		CallStoredProcedure proc = null;
		try {
			proc = new CallStoredProcedure("spFindCourseByID(?)");
			proc.setParameter(1, id);
			ResultSet results = proc.executeWithResults();
			if (null != results) {
				while (results.next()) {
					String title = results.getString(2);
					course.setId(id);
					course.setTitle(title);
				}
			}
			log.info("Course loaded courseID= {}, Title ={}", course.getId(), course.getTitle());
		} catch (SQLException e) {
			log.error("SQL Exception= {}", e.getMessage());
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
	}

	public boolean createCourse(InterfaceCourse course) {
		CallStoredProcedure proc = null;
		try {
			proc = new CallStoredProcedure("spCreateCourse(?, ?)");
			proc.setParameter(1, course.getTitle());
			proc.registerOutputParameterLong(2);
			proc.execute();
			log.info("Course created courseID= {}, Title ={}", course.getId(), course.getTitle());
		} catch (SQLException e) {
			log.error("Sql Exception ={}", e.getMessage());
			return false;
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return true;
	}

	public boolean deleteCourse(long id) {
		CallStoredProcedure proc = null;
		try {
			proc = new CallStoredProcedure("spDeleteCourse(?)");
			proc.setParameter(1, id);
			proc.execute();
			log.info("Course Deleted courseID={}", id);
		} catch (SQLException e) {
			log.error("Sql Exception = {}", e.getMessage());
			return false;
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return true;
	}
}

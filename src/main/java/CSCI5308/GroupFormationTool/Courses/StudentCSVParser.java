package CSCI5308.GroupFormationTool.Courses;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import CSCI5308.GroupFormationTool.AccessControl.InterfaceUser;
import CSCI5308.GroupFormationTool.AccessControl.UserFactory;
import CSCI5308.GroupFormationTool.AccessControl.UserObjectFactory;

public class StudentCSVParser implements IStudentCSVParser {

	private MultipartFile uploadedFile;
	private List<InterfaceUser> studentList = new ArrayList<>();

	public StudentCSVParser(MultipartFile file) {
		this.uploadedFile = file;

	}

	@Override
	public List<InterfaceUser> parseCSVFile(List<String> failureResults) {
		try {
			Reader reader = new InputStreamReader(uploadedFile.getInputStream());
			CSVReader csvReader = new CSVReaderBuilder(reader).build();
			List<String[]> records = csvReader.readAll();
			Iterator<String[]> iter = records.iterator();
			while (iter.hasNext()) {
				String[] record = iter.next();

				String bannerID = record[0];
				String firstName = record[1];
				String lastName = record[2];
				String email = record[3];

				InterfaceUser u = UserObjectFactory.createObject(new UserFactory());
				u.setBannerID(bannerID);
				u.setFirstName(firstName);
				u.setLastName(lastName);
				u.setEmail(email);
				studentList.add(u);
			}

		} catch (IOException e) {
			failureResults.add("Failure reading uploaded file: " + e.getMessage());
		} catch (Exception e) {
			failureResults.add("Failure parsing CSV file: " + e.getMessage());
		}

		return studentList;

	}

}

package CSCI5308.GroupFormationTool.GroupFormationAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class SurveyResponse implements ISurveyResponse {
	String bannerID;
	String firstName;
	String lastName;
	List<String> questions = new ArrayList<String>();
	List<String> responses = new ArrayList<String>();

	public String getBannerID() {
		return bannerID;
	}

	public List<String> getQuestions() {
		return questions;
	}

	public void setQuestions(List<String> questions) {
		this.questions = questions;
	}

	public List<String> getResponses() {
		return responses;
	}

	public void setResponses(List<String> responses) {
		this.responses = responses;
	}

	public void setBannerID(String bannerID) {
		this.bannerID = bannerID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}

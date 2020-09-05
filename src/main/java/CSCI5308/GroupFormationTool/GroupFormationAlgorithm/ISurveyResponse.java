package CSCI5308.GroupFormationTool.GroupFormationAlgorithm;

import java.util.List;

public interface ISurveyResponse {

	public String getBannerID() ;

	public void setBannerID(String bannerID) ;

	public String getFirstName() ;

	public void setFirstName(String firstName) ;

	public String getLastName() ;

	public void setLastName(String lastName) ;
	
	public void setQuestions(List<String> questions);
	
	public List<String> getQuestions();
	
	public void setResponses(List<String> responses);
	
	public List<String> getResponses();
	
}

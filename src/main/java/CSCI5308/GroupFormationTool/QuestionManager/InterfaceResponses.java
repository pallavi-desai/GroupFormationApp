package CSCI5308.GroupFormationTool.QuestionManager;

public interface InterfaceResponses {
	String getResponse_txt();

	void setResponse_txt(String response_txt);

	String getScore_txt();

	void setScore_txt(String score_txt);

	boolean insertResponses(IQuestionsPersistence questionDB);
}

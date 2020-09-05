package CSCI5308.GroupFormationTool.QuestionManager;

import java.util.HashMap;

public interface InterfaceQuestionModel {
	Long getUserID();

	void setUserID(Long userID);

	String getTypeSelect();

	void setTypeSelect(String typeSelect);

	HashMap<String, String> getQuestionTypeList();

	void setQuestionTypeList(HashMap<String, String> questionTypeList);

	String getQuestionTitle();

	void setQuestionTitle(String questionTitle);

	String getQuestionText();

	void setQuestionText(String questionText);

	String getResponseText();

	long getQuestionID();

	void setQuestionID(long questionID);

	String getQuestionType();

	void setQuestionType(String questionType);

	void setResponseText(String responseText);

	int getResponseScore();

	void setResponseScore(int responseScore);

	boolean createQuestion(IQuestionsPersistence questionDB);
}

package CSCI5308.GroupFormationTool.CreateSurvey;

public interface ICreateSurveyQuestionsModel {
	String[] getQuestionType();

	void setQuestionType(String[] questionType);

	String[] getQuestionHeading();

	void setQuestionHeading(String[] questionHeading);

	String[] getSelectedQuestions();

	void setSelectedQuestions(String[] selectedQuestions);

	String[] getSelectedTypes();

	void setSelectedTypes(String[] selectedTypes);

}

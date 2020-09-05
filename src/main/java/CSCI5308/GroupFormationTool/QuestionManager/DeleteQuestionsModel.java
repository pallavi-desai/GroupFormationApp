package CSCI5308.GroupFormationTool.QuestionManager;

public class DeleteQuestionsModel implements InterfaceDeleteQuestionsModel {
	private String[] listQuestions;
	private String[] selectedQuestions;

	@Override
	public String[] getListQuestions() {
		return listQuestions;
	}

	@Override
	public void setListQuestions(String[] listQuestions) {
		this.listQuestions = listQuestions;
	}

	@Override
	public String[] getSelectedQuestions() {
		return selectedQuestions;
	}

	@Override
	public void setSelectedQuestions(String[] selectedQuestions) {
		this.selectedQuestions = selectedQuestions;
	}
}

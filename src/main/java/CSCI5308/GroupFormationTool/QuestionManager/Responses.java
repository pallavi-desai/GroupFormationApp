package CSCI5308.GroupFormationTool.QuestionManager;

public class Responses implements InterfaceResponses {
	private String response_txt;
	private String score_txt;

	@Override
	public String getResponse_txt() {
		return response_txt;
	}

	@Override
	public void setResponse_txt(String response_txt) {
		this.response_txt = response_txt;
	}

	@Override
	public String getScore_txt() {
		return score_txt;
	}

	@Override
	public void setScore_txt(String score_txt) {
		this.score_txt = score_txt;
	}

	@Override
	public boolean insertResponses(IQuestionsPersistence questionDB) {
		return questionDB.insertResponses(this);
	}

}

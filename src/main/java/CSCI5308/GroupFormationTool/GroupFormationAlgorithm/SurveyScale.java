package CSCI5308.GroupFormationTool.GroupFormationAlgorithm;

import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.SystemConfig;

public class SurveyScale implements ISurveyScale {
	private String questiontext;
	private String questiontype;
	private String criteria;
	private String threshold;
	private String optionscount;
	private String questionid;

	public String getOptionscount() {
		return optionscount;
	}

	public void setOptionscount(String optionscount) {
		this.optionscount = optionscount;
	}

	public String getQuestionid() {
		return questionid;
	}

	public void setQuestionid(String questionid) {
		this.questionid = questionid;
	}

	public String getQuestiontext() {
		return questiontext;
	}

	public void setQuestiontext(String questiontext) {
		this.questiontext = questiontext;
	}

	public String getQuestiontype() {
		return questiontype;
	}

	public void setQuestiontype(String questiontype) {
		this.questiontype = questiontype;
	}

	public String getCriteria() {
		return criteria;
	}

	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}

	public String getThreshold() {
		return threshold;
	}

	public void setThreshold(String threshold) {
		this.threshold = threshold;
	}

	public double distance(ISurveyResponse rp1, ISurveyResponse rp2, int index) {
		return 0;
	}

	public List<ISurveyScale> convertor() {
		ISurveyScale tempObj = null;
		List<ISurveyScale> surveyScales;

		String criteria[] = this.getCriteria().split(",");
		String questionType[] = this.getQuestiontype().split(",");
		String questionText[] = this.getQuestiontext().split(",");
		String optionscount[] = this.getOptionscount().split(",");
		String questionId[] = this.getQuestionid().split(",");
		surveyScales = new ArrayList<ISurveyScale>();

		for (int i = 0; i < questionType.length; i++) {
			switch (questionType[i]) {
			case "mcq1":
				tempObj = SystemConfig.instance().getGroupFormationFactory().createSurveyScaleMCQ1();
				break;
			case "mcq2":
				tempObj = SystemConfig.instance().getGroupFormationFactory().createSurveyScaleMCQ2();
				break;
			case "numeric":
				tempObj = SystemConfig.instance().getGroupFormationFactory().createSurveyScaleNumeric();
				break;
			case "text":
				tempObj = SystemConfig.instance().getGroupFormationFactory().createSurveyScaleText();
				break;
			default:
				tempObj = SystemConfig.instance().getGroupFormationFactory().createSurveyScaleObject();
			}

			tempObj.setCriteria(criteria[i]);
			tempObj.setQuestiontype(questionType[i]);
			tempObj.setQuestiontext(questionText[i]);
			tempObj.setQuestionid(questionId[i]);
			tempObj.setOptionscount(optionscount[i]);
			surveyScales.add(tempObj);
		}
		return surveyScales;
	}

}
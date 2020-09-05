package CSCI5308.GroupFormationTool.GroupFormationAlgorithm;

import java.util.List;

public interface ISurveyScale {

	public String getQuestiontext();

	public void setQuestiontext(String questiontext);

	public String getQuestiontype();

	public void setQuestiontype(String questiontype);

	public String getCriteria();

	public void setCriteria(String criteria);

	public String getThreshold();

	public void setThreshold(String threshold);

	public String getOptionscount();

	public void setOptionscount(String optionscount);

	public String getQuestionid();

	public void setQuestionid(String questionid);

	public double distance(ISurveyResponse rp1, ISurveyResponse rp2, int index);

	public List<ISurveyScale> convertor();
}
package CSCI5308.GroupFormationTool.QuestionManager;

import java.util.List;

public interface IQuestionSorters {
	List<InterfaceQuestionModel> sort(String bannerID, InterfaceSorters interfaceSorters);

	List<InterfaceQuestionModel> clearSort(String bannerID);
}

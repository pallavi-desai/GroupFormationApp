package CSCI5308.GroupFormationTool.QuestionManager;

import java.util.Map;

public interface InterfaceSorters {
	String getSortField();

	void setSortField(String sortField);

	String getSortOrder();

	void setSortOrder(String sortOrder);

	Map<String, String> sortingFieldList();

	Map<String, String> sortingOrderList();
}

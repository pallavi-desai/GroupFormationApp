package CSCI5308.GroupFormationTool.QuestionManager;

import java.util.LinkedHashMap;
import java.util.Map;

public class Sorters implements InterfaceSorters {
	private String sortField;
	private String sortOrder;

	@Override
	public String getSortField() {
		return sortField;
	}

	@Override
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	@Override
	public String getSortOrder() {
		return sortOrder;
	}

	@Override
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	@Override
	public Map<String, String> sortingFieldList() {
		Map<String, String> sortingFields = new LinkedHashMap<>();
		sortingFields.put("question_title", "Question title");
		sortingFields.put("date_created", "Date created");
		return sortingFields;
	}

	@Override
	public Map<String, String> sortingOrderList() {
		Map<String, String> sortingOrders = new LinkedHashMap<>();
		sortingOrders.put("ASC", "Ascending");
		sortingOrders.put("DESC", "Desceding");
		return sortingOrders;
	}

}

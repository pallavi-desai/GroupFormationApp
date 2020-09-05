package CSCI5308.GroupFormationTool.QuestionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

public class SortingDB implements IQuestionSorters {
	private static final Logger log = LoggerFactory.getLogger(SortingDB.class);

	IQuestionsPersistence interfaceQuestionDB = QManagerDbFactory.FactorySingleton().createQuestionDB();

	public List<InterfaceQuestionModel> sort(String bannerID, InterfaceSorters interfaceSorters) {
		List<InterfaceQuestionModel> questions = new ArrayList<>();
		CallStoredProcedure proc = null;
		try {
			proc = new CallStoredProcedure("spGetSortedQuestions(?,?,?)");
			proc.setParameter(1, bannerID);
			proc.setParameter(2, interfaceSorters.getSortField());
			proc.setParameter(3, interfaceSorters.getSortOrder());
			ResultSet results = proc.executeWithResults();
			if (null != results) {
				while (results.next()) {
					String title = results.getString(1);
					InterfaceQuestionModel interfaceQuestionModel = QManagerModelFactory.FactorySingleton()
							.createQuestionModel();
					interfaceQuestionModel.setQuestionTitle(title);
					questions.add(interfaceQuestionModel);
				}
				log.info("Questions sorted by SortField = {} in SortOrder={} for User ={}",
						interfaceSorters.getSortField(), interfaceSorters.getSortOrder(), bannerID);
			}
		} catch (SQLException e) {
			log.error("Sql Exception = {}", e.getMessage());
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return questions;
	}

	public List<InterfaceQuestionModel> clearSort(String bannerID) {
		return interfaceQuestionDB.loadAllQuestionsByID(bannerID);
	}
}

package CSCI5308.GroupFormationTool.QuestionManager;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class SortersTest {
    IQManagerModelFactory modelFactory = QManagerModelFactory.FactorySingleton();

    @Test
    void getSortField() {
        InterfaceSorters interfaceSorters = modelFactory.createSorters();
        interfaceSorters.setSortField("questionID");
        assertTrue(interfaceSorters.getSortField().equals("questionID"));
    }

    @Test
    void setSortField() {
        InterfaceSorters interfaceSorters = modelFactory.createSorters();
        interfaceSorters.setSortField("questionID");
        assertTrue(interfaceSorters.getSortField().equals("questionID"));
    }

    @Test
    void getSortOrder() {
        InterfaceSorters interfaceSorters = modelFactory.createSorters();
        interfaceSorters.setSortOrder("ASC");
        assertTrue(interfaceSorters.getSortOrder().equals("ASC"));
    }

    @Test
    void setSortOrder() {
        InterfaceSorters interfaceSorters = modelFactory.createSorters();
        interfaceSorters.setSortOrder("ASC");
        assertTrue(interfaceSorters.getSortOrder().equals("ASC"));
    }

    @Test
    void sortingFieldList() {
        Map<String,String> sortingFields = new LinkedHashMap<>();
        sortingFields.put("question_title","Question title");
        assertTrue(sortingFields.get("question_title").equals("Question title"));
    }

    @Test
    void sortingOrderList() {
        Map<String,String> sortingOrders = new LinkedHashMap<>();
        sortingOrders.put("ASC","Ascending");
        assertTrue(sortingOrders.get("ASC").equals("Ascending"));
    }

}
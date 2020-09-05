package CSCI5308.GroupFormationTool.QuestionManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.validateMockitoUsage;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

class SortingDBTest {
    @Mock
    CallStoredProcedure procedure;

    @Mock
    ResultSet rs;

    @Mock
    private SortingDB sortingDB;
    private InterfaceSorters interfaceSorters;
    private IQManagerModelFactory modelFactory;
    String bannerID;
    List<InterfaceQuestionModel> questionList = new ArrayList<>();

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        assertNotNull(procedure);

        modelFactory= QManagerModelFactory.FactorySingleton();
        interfaceSorters = modelFactory.createSorters();
        interfaceSorters.setSortField("question_title");
        interfaceSorters.setSortOrder("ASC");
        bannerID = "B999999";
        InterfaceQuestionModel q1 = modelFactory.createQuestionModel();
        InterfaceQuestionModel q2 = modelFactory.createQuestionModel();
        q1.setQuestionTitle("Skills");
        q2.setQuestionTitle("Credits");
        questionList.add(q1);
        questionList.add(q2);
        Collections.sort(questionList, Comparator.comparing(InterfaceQuestionModel::getQuestionTitle));

        when(procedure.executeWithResults()).thenReturn(rs);
        when(sortingDB.sort(bannerID, interfaceSorters)).thenReturn(questionList);

        InterfaceQuestionModel q3 = modelFactory.createQuestionModel();
        q3.setQuestionTitle("Project");
        questionList.add(q3);
        when(sortingDB.clearSort(bannerID)).thenReturn(questionList);

    }

    @AfterEach
    void tearDown() {
        validateMockitoUsage();
    }

    @Test
    void sort() {
        assertEquals(sortingDB.sort(bannerID, interfaceSorters),questionList);
        Mockito.verify(sortingDB).sort(bannerID, interfaceSorters);
    }

    @Test
    void clearSort() {
        assertEquals(sortingDB.clearSort(bannerID),questionList);
        Mockito.verify(sortingDB).clearSort(bannerID);
    }
}
package CSCI5308.GroupFormationTool.GroupFormationAlgorithmTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.validateMockitoUsage;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.QuestionManager.IQManagerModelFactory;
import CSCI5308.GroupFormationTool.QuestionManager.InterfaceQuestionModel;
import CSCI5308.GroupFormationTool.QuestionManager.InterfaceResponses;
import CSCI5308.GroupFormationTool.QuestionManager.QManagerModelFactory;
import CSCI5308.GroupFormationTool.QuestionManager.QuestionModel;
import CSCI5308.GroupFormationTool.QuestionManager.Responses;
import CSCI5308.GroupFormationTool.SurveyResponses.SurveyResponseDB;

class SurveyResponseDBTest {
    @Mock
    CallStoredProcedure procedure;

    @Mock
    ResultSet rs;

    @Mock
    private SurveyResponseDB surveyResponseDB;
    InterfaceQuestionModel interfaceQuestionModel;
    InterfaceResponses interfaceResponses;
    IQManagerModelFactory modelFactory;

    HashMap<Integer, Integer> surveyAvailable = new HashMap<>();
    List<InterfaceQuestionModel> surveyQuestions = new ArrayList<>();
    Map<Long, ArrayList<InterfaceResponses>> surveyResponse = new HashMap<>();
    ArrayList<InterfaceResponses> responses = new ArrayList<>();

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        assertNotNull(procedure);
        surveyAvailable.put(1,0);
        when(procedure.executeWithResults()).thenReturn(rs);
        when(surveyResponseDB.checkIfSurveyPublished(8)).thenReturn(surveyAvailable);

        modelFactory= QManagerModelFactory.FactorySingleton();
        interfaceQuestionModel= modelFactory.createQuestionModel();
        interfaceQuestionModel= new QuestionModel();
        interfaceQuestionModel.setQuestionText("Rate your java skills");
        interfaceQuestionModel.setQuestionID(1);
        interfaceQuestionModel.setQuestionType("numeric");
        surveyQuestions.add(interfaceQuestionModel);
        when(surveyResponseDB.getSurveyQuestions(1)).thenReturn(surveyQuestions);

        interfaceResponses= modelFactory.createResponses();
        interfaceResponses= new Responses();
        interfaceResponses.setResponse_txt("Java");
        interfaceResponses.setScore_txt("1");
        responses.add(interfaceResponses);
        surveyResponse.put((long) 65,responses);
        when(surveyResponseDB.getSurveyResponses()).thenReturn(surveyResponse);
    }

    @AfterEach
    void tearDown() {
        validateMockitoUsage();
    }

    @Test
    void checkIfSurveyPublished() {
        HashMap<Integer, Integer> surveyAvailableTest = surveyResponseDB.checkIfSurveyPublished(8);
        for (Map.Entry<Integer, Integer> entry : surveyAvailableTest.entrySet()) {
            int published = entry.getValue();
            assertTrue(published == 0);
        }
    }

    @Test
    void getSurveyQuestions() {
        assertEquals(surveyResponseDB.getSurveyQuestions(1),surveyQuestions);
    }

    @Test
    void getSurveyResponses() {
        assertEquals(surveyResponseDB.getSurveyResponses(),surveyResponse);
    }
}
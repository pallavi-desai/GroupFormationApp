package CSCI5308.GroupFormationTool.GroupFormationAlgorithmTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.SurveyResponses.SurveyResultDB;

class SurveyResultDBTest {
    @Mock
    CallStoredProcedure procedure;

    @Mock
    ResultSet rs;

    @Mock
    private SurveyResultDB surveyResultDB;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        assertNotNull(procedure);
        when(procedure.executeWithResults()).thenReturn(rs);
        when(surveyResultDB.checkIfResponseSubmitted(19,1)).thenReturn(true);
        when(surveyResultDB.checkIfResponseSubmitted(19,2)).thenReturn(false);
        when(surveyResultDB.submitSurveyResponse(22,1,69,"Three")).thenReturn(true);
        when(surveyResultDB.submitSurveyResponse(22,1,50,"Java")).thenReturn(false);
    }

    @Test
    void checkIfResponseSubmitted() {
        assertTrue(surveyResultDB.checkIfResponseSubmitted(19,1));
        assertFalse(surveyResultDB.checkIfResponseSubmitted(19,2));
    }

    @Test
    void submitSurveyResponse() {
        assertTrue(surveyResultDB.submitSurveyResponse(22,1,69,"Three"));
        assertFalse(surveyResultDB.submitSurveyResponse(22,1,50,"Java"));
    }
}
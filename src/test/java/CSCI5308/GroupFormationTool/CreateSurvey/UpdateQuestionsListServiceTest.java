package CSCI5308.GroupFormationTool.CreateSurvey;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

class UpdateQuestionsListServiceTest {

    IUpdateQuestionsListService updateQuestionsListService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        updateQuestionsListService = new UpdateQuestionsListService();
    }

    @Test
    void displayUpdatedQuestionList() {
        String[] questionHeading = {"queHead1", "queHead2"};
        String[] questionType = {"queType1", "queType2"};
        String que = "queHead1";
        String[] selectedTypes = {"queType1"};
        String[] selectedQuestions = {"queHead1"};
        String[] updatedHeading = {"queHead2"};
        String[] updatedType = {"queType2"};
        ICreateSurveyQuestionsModel createSurveyQuestionsModel = CreateSurveyModelFactory.FactorySingleton().createSurveyQuestionsModel();
        createSurveyQuestionsModel.setQuestionHeading(updatedHeading);
        createSurveyQuestionsModel.setQuestionType(updatedType);
        createSurveyQuestionsModel.setSelectedQuestions(selectedQuestions);
        createSurveyQuestionsModel.setSelectedTypes(selectedTypes);
        assertEquals(createSurveyQuestionsModel, updateQuestionsListService.displayUpdatedQuestionList(questionHeading, questionType, que));
    }

    @Test
    void removeQuestions() {
        String que = "queHead1";
        String[] selectedTypes = {"queType1"};
        String[] selectedQuestions = {"queHead1"};
        String[] updatedHeading = {"queHead2"};
        String[] updatedType = {"queType2"};
        ICreateSurveyQuestionsModel createSurveyQuestionsModel = CreateSurveyModelFactory.FactorySingleton().createSurveyQuestionsModel();
        createSurveyQuestionsModel.setQuestionHeading(updatedHeading);
        createSurveyQuestionsModel.setQuestionType(updatedType);
        createSurveyQuestionsModel.setSelectedQuestions(selectedQuestions);
        createSurveyQuestionsModel.setSelectedTypes(selectedTypes);
        assertEquals(createSurveyQuestionsModel, updateQuestionsListService.removeQuestions(que));
    }
}
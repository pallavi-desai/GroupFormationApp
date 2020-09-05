package CSCI5308.GroupFormationTool.CreateSurvey;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CreateSurveyQuestionsModelTest {

    @Test
    void getSelectedTypes() {
        String[] selectedTypes = {"test1", "test2"};
        ICreateSurveyQuestionsModel iCreateSurveyQuestionsModel = CreateSurveyModelFactory.FactorySingleton().createSurveyQuestionsModel();
        iCreateSurveyQuestionsModel.setSelectedTypes(selectedTypes);
        assertEquals(selectedTypes, iCreateSurveyQuestionsModel.getSelectedTypes());
    }

    @Test
    void setSelectedTypes() {
        String[] selectedTypes = {"test1", "test2"};
        ICreateSurveyQuestionsModel iCreateSurveyQuestionsModel = CreateSurveyModelFactory.FactorySingleton().createSurveyQuestionsModel();
        iCreateSurveyQuestionsModel.setSelectedTypes(selectedTypes);
        assertEquals(selectedTypes, iCreateSurveyQuestionsModel.getSelectedTypes());
    }

    @Test
    void getQuestionType() {
        String[] questionType = {"question1", "question2"};
        ICreateSurveyQuestionsModel iCreateSurveyQuestionsModel = CreateSurveyModelFactory.FactorySingleton().createSurveyQuestionsModel();
        iCreateSurveyQuestionsModel.setQuestionType(questionType);
        assertEquals(questionType, iCreateSurveyQuestionsModel.getQuestionType());
    }

    @Test
    void setQuestionType() {
        String[] questionType = {"question1", "question2"};
        ICreateSurveyQuestionsModel iCreateSurveyQuestionsModel = CreateSurveyModelFactory.FactorySingleton().createSurveyQuestionsModel();
        iCreateSurveyQuestionsModel.setQuestionType(questionType);
        assertEquals(questionType, iCreateSurveyQuestionsModel.getQuestionType());
    }

    @Test
    void getQuestionHeading() {
        String[] questionHeading = {"quehead1", "quehead2"};
        ICreateSurveyQuestionsModel iCreateSurveyQuestionsModel = CreateSurveyModelFactory.FactorySingleton().createSurveyQuestionsModel();
        iCreateSurveyQuestionsModel.setQuestionHeading(questionHeading);
        assertEquals(questionHeading, iCreateSurveyQuestionsModel.getQuestionHeading());
    }

    @Test
    void setQuestionHeading() {
        String[] questionHeading = {"quehead1", "quehead2"};
        ICreateSurveyQuestionsModel iCreateSurveyQuestionsModel = CreateSurveyModelFactory.FactorySingleton().createSurveyQuestionsModel();
        iCreateSurveyQuestionsModel.setQuestionHeading(questionHeading);
        assertEquals(questionHeading, iCreateSurveyQuestionsModel.getQuestionHeading());
    }

    @Test
    void getSelectedQuestions() {
        String[] selectedQue = {"quehead1", "quehead2"};
        ICreateSurveyQuestionsModel iCreateSurveyQuestionsModel = CreateSurveyModelFactory.FactorySingleton().createSurveyQuestionsModel();
        iCreateSurveyQuestionsModel.setSelectedQuestions(selectedQue);
        assertEquals(selectedQue, iCreateSurveyQuestionsModel.getSelectedQuestions());
    }

    @Test
    void setSelectedQuestions() {
        String[] selectedQue = {"quehead1", "quehead2"};
        ICreateSurveyQuestionsModel iCreateSurveyQuestionsModel = CreateSurveyModelFactory.FactorySingleton().createSurveyQuestionsModel();
        iCreateSurveyQuestionsModel.setSelectedQuestions(selectedQue);
        assertEquals(selectedQue, iCreateSurveyQuestionsModel.getSelectedQuestions());
    }
}
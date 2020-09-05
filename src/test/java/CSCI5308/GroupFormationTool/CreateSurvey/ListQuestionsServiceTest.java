package CSCI5308.GroupFormationTool.CreateSurvey;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Dictionary;
import java.util.Hashtable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

class ListQuestionsServiceTest {
    private IListQuestionsService listQuestionsService;
    private IQueryQuestionsRepo queryQuestionsRepo;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        queryQuestionsRepo = mock(QueryQuestionsRepo.class);
        listQuestionsService = new ListQuestionsService();
        CreateSurveyDBFactory.FactorySingleton().setQueryQuestionsRepo(queryQuestionsRepo);

    }

    @Test
    void listAllQuestionsforUser() {
        String[] questionHeading = {"questionHead1", "questionHead2"};
        String[] questionType = {"questionType1", "questionType2"};
        long userID = 1;
        Dictionary<String, String> hashmap = new Hashtable<>();

        for (int i = 0; i < questionHeading.length; i++) {
            hashmap.put(questionHeading[i], questionType[i]);
        }
        ICreateSurveyQuestionsModel createSurveyQuestionsModel = CreateSurveyModelFactory.FactorySingleton().createSurveyQuestionsModel();
        createSurveyQuestionsModel.setQuestionHeading(questionHeading);
        createSurveyQuestionsModel.setQuestionType(questionType);
        when(queryQuestionsRepo.listQuestionsForUser(userID)).thenReturn(createSurveyQuestionsModel);
        assertEquals(hashmap, listQuestionsService.listAllQuestionsforUser(userID));
    }

    @Test
    void listRepeatQuestions() {
        String[] questionHeading = {"questionHead1", "questionHead2"};
        String[] questionType = {"questionType1", "questionType2"};
        Dictionary<String, String> hashmap = new Hashtable<>();

        for (int i = 0; i < questionHeading.length; i++) {
            hashmap.put(questionHeading[i], questionType[i]);
        }
        ICreateSurveyQuestionsModel createSurveyQuestionsModel = CreateSurveyModelFactory.FactorySingleton().createSurveyQuestionsModel();
        createSurveyQuestionsModel.setQuestionHeading(questionHeading);
        createSurveyQuestionsModel.setQuestionType(questionType);
        assertEquals(hashmap, listQuestionsService.listRepeatQuestions());
    }
}
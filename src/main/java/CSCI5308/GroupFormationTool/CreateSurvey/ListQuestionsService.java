package CSCI5308.GroupFormationTool.CreateSurvey;

import java.util.Dictionary;
import java.util.Hashtable;

public class ListQuestionsService implements IListQuestionsService {


    ICreateSurveyModelFactory iCreateSurveyModelFactory;
    ICreateSurveyDBFactory iCreateSurveyDBFactory;
    ICreateSurveyQuestionsModel iCreateSurveyQuestionsModel;
    IQueryQuestionsRepo iQueryQuestionsRepo;

    @Override
    public Dictionary listAllQuestionsforUser(long userID) {
        iCreateSurveyModelFactory = CreateSurveyModelFactory.FactorySingleton();
        iCreateSurveyQuestionsModel = iCreateSurveyModelFactory.createSurveyQuestionsModel();
        iCreateSurveyDBFactory = CreateSurveyDBFactory.FactorySingleton();
        iQueryQuestionsRepo = iCreateSurveyDBFactory.queryQuestionsRepo();

        Dictionary hashmap = new Hashtable<>();
        iCreateSurveyQuestionsModel = iQueryQuestionsRepo.listQuestionsForUser(userID);
        for (int i = 0; i < iCreateSurveyQuestionsModel.getQuestionHeading().length; i++) {
            hashmap.put(iCreateSurveyQuestionsModel.getQuestionHeading()[i], iCreateSurveyQuestionsModel.getQuestionType()[i]);
        }
        return hashmap;
    }

    @Override
    public Dictionary listRepeatQuestions() {
        iCreateSurveyModelFactory = CreateSurveyModelFactory.FactorySingleton();
        iCreateSurveyQuestionsModel = iCreateSurveyModelFactory.createSurveyQuestionsModel();

        Dictionary hashmap1 = new Hashtable<>();
        for (int i = 0; i < iCreateSurveyQuestionsModel.getQuestionHeading().length; i++) {
            hashmap1.put(iCreateSurveyQuestionsModel.getQuestionHeading()[i], iCreateSurveyQuestionsModel.getQuestionType()[i]);
        }
        return hashmap1;
    }
}

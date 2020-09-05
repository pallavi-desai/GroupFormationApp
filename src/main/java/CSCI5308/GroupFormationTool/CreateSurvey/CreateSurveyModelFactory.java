package CSCI5308.GroupFormationTool.CreateSurvey;

public class CreateSurveyModelFactory implements ICreateSurveyModelFactory {

    private static ICreateSurveyModelFactory iCreateSurveyModelFactory = null;
    private ICreateSurveyQuestionsModel createSurveyQuestionsModel;
    private IListQuestionsService listQuestionsService;
    private IUpdateQuestionsListService updateQuestionsListService;

    public static ICreateSurveyModelFactory FactorySingleton() {
        if (iCreateSurveyModelFactory == null) {
            iCreateSurveyModelFactory = new CreateSurveyModelFactory();
        }
        return iCreateSurveyModelFactory;
    }


    @Override
    public ICreateSurveyQuestionsModel createSurveyQuestionsModel() {

        if (null == createSurveyQuestionsModel) {
            createSurveyQuestionsModel = new CreateSurveyQuestionsModel();
        }
        return createSurveyQuestionsModel;
    }

    @Override
    public IUpdateQuestionsListService getUpdateQuestionsListService() {
        if (null == updateQuestionsListService) {
            updateQuestionsListService = new UpdateQuestionsListService();
        }
        return updateQuestionsListService;
    }

    @Override
    public IListQuestionsService getListQuestionsService() {
        if (null == listQuestionsService) {
            listQuestionsService = new ListQuestionsService();
        }
        return listQuestionsService;
    }
}

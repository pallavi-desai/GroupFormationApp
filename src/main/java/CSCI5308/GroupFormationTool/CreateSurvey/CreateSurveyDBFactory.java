package CSCI5308.GroupFormationTool.CreateSurvey;

public class CreateSurveyDBFactory implements ICreateSurveyDBFactory {
    private static ICreateSurveyDBFactory createSurveyDBFactory = null;
    private ICreateSurveyDB createSurveyDB;
    private IQueryQuestionsRepo queryQuestionsRepo;
    private ISurveyExistRepo surveyExistRepo;


    public static ICreateSurveyDBFactory FactorySingleton() {
        if (createSurveyDBFactory == null) {
            createSurveyDBFactory = new CreateSurveyDBFactory();
        }
        return createSurveyDBFactory;
    }

    @Override
    public ICreateSurveyDB createSurveyDB() {
        if (null == createSurveyDB) {
            createSurveyDB = new CreateSurveyDB();
        }
        return createSurveyDB;
    }

    @Override
    public IQueryQuestionsRepo queryQuestionsRepo() {

        if (null == queryQuestionsRepo) {
            queryQuestionsRepo = new QueryQuestionsRepo();
        }
        return queryQuestionsRepo;
    }

    @Override
    public ISurveyExistRepo surveyExistRepo() {
        if (null == surveyExistRepo) {
            surveyExistRepo = new SurveyExistRepo();
        }
        return surveyExistRepo;
    }

    @Override
    public void setQueryQuestionsRepo(IQueryQuestionsRepo queryQuestionsRepo) {
        this.queryQuestionsRepo = queryQuestionsRepo;
    }


}

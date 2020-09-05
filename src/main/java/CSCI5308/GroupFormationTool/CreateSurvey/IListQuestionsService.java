package CSCI5308.GroupFormationTool.CreateSurvey;

import java.util.Dictionary;

public interface IListQuestionsService {
    Dictionary listAllQuestionsforUser(long userID);

    Dictionary listRepeatQuestions();
}

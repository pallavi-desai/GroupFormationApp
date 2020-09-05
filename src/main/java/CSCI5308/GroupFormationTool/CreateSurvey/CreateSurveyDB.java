package CSCI5308.GroupFormationTool.CreateSurvey;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

public class CreateSurveyDB implements ICreateSurveyDB {
    private static final Logger logger = LoggerFactory.getLogger(CreateSurveyDB.class);
    ICreateSurveyModelFactory iCreateSurveyModelFactory;
    ICreateSurveyQuestionsModel iCreateSurveyQuestionsModel;
    ICreateSurveyDBFactory iCreateSurveyDBFactory;
    ISurveyExistRepo iSurveyExistRepo;

    @Override
    public boolean saveSurvey(long courseID, long userID, int status) {
        iCreateSurveyModelFactory = CreateSurveyModelFactory.FactorySingleton();
        iCreateSurveyQuestionsModel = iCreateSurveyModelFactory.createSurveyQuestionsModel();
        iCreateSurveyDBFactory = CreateSurveyDBFactory.FactorySingleton();
        iSurveyExistRepo = iCreateSurveyDBFactory.surveyExistRepo();
        int surveyID;
        int state;
        CallStoredProcedure proc = null;
        CallStoredProcedure procedure = null;
        CallStoredProcedure procedure1 = null;
        CallStoredProcedure procedure2 = null;
        ArrayList<Integer> questionsID = new ArrayList<>();
        String[] selectedQuestions;

        try {
            if (status == 0) {
                state = iSurveyExistRepo.checkSurveyStatus(courseID);
                selectedQuestions = iCreateSurveyQuestionsModel.getSelectedQuestions();

                if (state == 2) {
                    createNewSurvey(courseID, status);
                }
                surveyID = fetchSurveyID(courseID, status);
                proc = new CallStoredProcedure("spDeleteSavedQuestions(?)");
                proc.setParameter(1, surveyID);
                proc.execute();

                for (String selectedQuestion : selectedQuestions) {
                    procedure = new CallStoredProcedure("spGetQuestionIDs(?,?,?)");
                    procedure.setParameter(1, userID);
                    procedure.setParameter(2, selectedQuestion);
                    procedure.setParameter(3, surveyID);
                    ResultSet resultSet = procedure.executeWithResults();
                    if (null != resultSet) {
                        while (resultSet.next()) {
                            questionsID.add(resultSet.getInt(1));
                        }
                    }
                }

                for (Integer integer : questionsID) {
                    Calendar cal = Calendar.getInstance();
                    java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());
                    procedure1 = new CallStoredProcedure("spSaveSurveyQuestions(?,?,?)");
                    procedure1.setParameter(1, surveyID);
                    procedure1.setParameter(2, integer);
                    procedure1.setParameter(3, timestamp);
                    procedure1.execute();
                }
                logger.info(" Survey save state= {}", "Success");
                return true;
            }
            if (status == 1) {
                procedure2 = new CallStoredProcedure("spPublishSurvey(?)");
                procedure2.setParameter(1, courseID);
                procedure2.execute();
                logger.info(" Survey has been published  state= {}", "Success");
                return true;
            }
        } catch (SQLException e) {
            logger.error(" Survey could not be saved  state= {}, message={}", "Fail", e.getMessage());
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
            if (null != procedure) {
                procedure.cleanup();
            }
            if (null != procedure1) {
                procedure1.cleanup();
            }
            if (null != procedure2) {
                procedure2.cleanup();
            }
        }
        logger.info("Survey saved status={}", "Success");
        return false;
    }

    @Override
    public void createNewSurvey(long courseID, long status) {
        CallStoredProcedure callStoredProcedure = null;
        try {
            callStoredProcedure = new CallStoredProcedure("spCreateNewSurvey(?,?)");
            callStoredProcedure.setParameter(1, courseID);
            callStoredProcedure.setParameter(2, status);
            callStoredProcedure.execute();
            logger.info(" New survey creation  state= {}", "Success");

        } catch (SQLException e) {
            logger.error(" Survey could not be created  state= {}, message={}", "Fail", e.getMessage());
        } finally {
            if (null != callStoredProcedure) {
                callStoredProcedure.cleanup();
            }
        }
    }

    @Override
    public int fetchSurveyID(long courseID, int state) {
        int surveyID = 0;
        CallStoredProcedure procedure = null;
        try {
            procedure = new CallStoredProcedure("spFetchSurveyID(?,?)");
            procedure.setParameter(1, courseID);
            procedure.setParameter(2, state);
            ResultSet resultSet = procedure.executeWithResults();

            if (null != resultSet) {
                while (resultSet.next()) {
                    surveyID = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            logger.error(" ID could not be retrieved  state= {}, message={}", "Fail", e.getMessage());
        } finally {
            if (null != procedure) {
                procedure.cleanup();
            }
        }
        return surveyID;
    }

    @Override
    public boolean fetchSavedQuestions(long courseID) {
        iCreateSurveyModelFactory = CreateSurveyModelFactory.FactorySingleton();
        iCreateSurveyQuestionsModel = iCreateSurveyModelFactory.createSurveyQuestionsModel();
        iCreateSurveyDBFactory = CreateSurveyDBFactory.FactorySingleton();
        iSurveyExistRepo = iCreateSurveyDBFactory.surveyExistRepo();
        int state;
        int surveyID = 0;
        ArrayList<String> questions = new ArrayList<>();
        ArrayList<String> questionType = new ArrayList<>();
        state = iSurveyExistRepo.checkSurveyStatus(courseID);
        CallStoredProcedure procedure1 = null;

        try {
            if (state == 0) {

                surveyID = fetchSurveyID(courseID, state);
                procedure1 = new CallStoredProcedure("spGetSavedData(?)");
                procedure1.setParameter(1, surveyID);
                ResultSet resultSet1 = procedure1.executeWithResults();

                if (null != resultSet1) {
                    while (resultSet1.next()) {
                        questions.add(resultSet1.getString(1));
                        questionType.add(resultSet1.getString(2));
                    }

                    iCreateSurveyQuestionsModel.setSelectedQuestions(questions.toArray(new String[questions.size()]));
                    iCreateSurveyQuestionsModel.setSelectedTypes(questionType.toArray(new String[questionType.size()]));
                }
                logger.info(" Saved fetch status  state= {}", "Success");
            } else if (state == 1) {
                return false;
            }
        } catch (SQLException e) {
            logger.error(" Saved questions can not be retrieved state= {}, message={}", "Fail", e.getMessage());
        } finally {
            if (null != procedure1) {
                procedure1.cleanup();
            }
        }
        return true;
    }

    @Override
    public boolean updatePublishStatus(long courseID) {

        CallStoredProcedure callStoredProcedure = null;
        try {
            callStoredProcedure = new CallStoredProcedure("spChangePublishStatus(?)");
            callStoredProcedure.setParameter(1, courseID);
            callStoredProcedure.execute();
            logger.info(" Survey publish status state= {}", "Success");
        } catch (SQLException e) {
            logger.error(" Survey can not be unpublished  state= {}, message={}", "Fail", e.getMessage());
            return false;
        } finally {
            if (null != callStoredProcedure) {
                callStoredProcedure.cleanup();
            }
        }
        logger.info("Survey unpublished status={}", "Success");
        return true;
    }
}

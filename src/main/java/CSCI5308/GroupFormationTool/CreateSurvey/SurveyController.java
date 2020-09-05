package CSCI5308.GroupFormationTool.CreateSurvey;

import java.sql.SQLException;
import java.util.Dictionary;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SurveyController {

    ICreateSurveyModelFactory iCreateSurveyModelFactory;
    ICreateSurveyQuestionsModel iCreateSurveyQuestionsModel;
    IListQuestionsService iListQuestionsService;
    IUpdateQuestionsListService iUpdateQuestionsListService;
    ICreateSurveyDBFactory iCreateSurveyDBFactory;
    ICreateSurveyDB iCreateSurveyDB;

    @RequestMapping("/surveyhome")
    public ModelAndView surveyHome(@RequestParam(name = "id") long courseID, @RequestParam(name = "userID") long userID) throws SQLException {
        iCreateSurveyModelFactory = CreateSurveyModelFactory.FactorySingleton();
        iCreateSurveyQuestionsModel = iCreateSurveyModelFactory.createSurveyQuestionsModel();
        iListQuestionsService = iCreateSurveyModelFactory.getListQuestionsService();

        iCreateSurveyDBFactory = CreateSurveyDBFactory.FactorySingleton();
        iCreateSurveyDB = iCreateSurveyDBFactory.createSurveyDB();


        boolean surveyFlag;
        ModelAndView mv = new ModelAndView("CreateSurvey/surveyhome");
        surveyFlag = iCreateSurveyDB.fetchSavedQuestions(courseID);
        mv.addObject("courseID", courseID);
        mv.addObject("userID", userID);
        if (surveyFlag == false) {
            mv.addObject("surveyFlag", false);
            mv.addObject("surveyMessage", "A Survey is already published.");
            return mv;
        }
        Dictionary hashMap = iListQuestionsService.listAllQuestionsforUser(userID);
        mv.addObject("surveyFlag", true);
        mv.addObject("courseID", courseID);
        mv.addObject("userID", userID);
        mv.addObject("questionsList", hashMap);
        mv.addObject("selectedQuestions", iCreateSurveyQuestionsModel.getSelectedQuestions());
        mv.addObject("publish", false);
        return mv;
    }


    @RequestMapping("/addQuestions")
    public ModelAndView addQuestions(@RequestParam(name = "selectedQue") String que, @RequestParam(name = "id") long courseID, @RequestParam(name = "userID") long userID) {
        iCreateSurveyModelFactory = CreateSurveyModelFactory.FactorySingleton();
        iCreateSurveyQuestionsModel = iCreateSurveyModelFactory.createSurveyQuestionsModel();
        iListQuestionsService = iCreateSurveyModelFactory.getListQuestionsService();
        iUpdateQuestionsListService = iCreateSurveyModelFactory.getUpdateQuestionsListService();

        ModelAndView mv = new ModelAndView("CreateSurvey/surveyhome");
        iCreateSurveyQuestionsModel = iUpdateQuestionsListService.displayUpdatedQuestionList(iCreateSurveyQuestionsModel.getQuestionHeading(), iCreateSurveyQuestionsModel.getQuestionType(), que);
        mv.addObject("publish", false);
        mv.addObject("surveyFlag", true);
        mv.addObject("courseID", courseID);
        mv.addObject("userID", userID);
        mv.addObject("questionsList", iListQuestionsService.listRepeatQuestions());
        mv.addObject("selectedQuestions", iCreateSurveyQuestionsModel.getSelectedQuestions());
        mv.addObject("selectedType", iCreateSurveyQuestionsModel.getSelectedTypes());
        return mv;
    }

    @RequestMapping("/removeQuestions")
    public ModelAndView removeQuestions(@RequestParam(name = "removeQue") String que, @RequestParam(name = "id") long courseID, @RequestParam(name = "userID") long userID) {
        iCreateSurveyModelFactory = CreateSurveyModelFactory.FactorySingleton();
        iCreateSurveyQuestionsModel = iCreateSurveyModelFactory.createSurveyQuestionsModel();
        iUpdateQuestionsListService = iCreateSurveyModelFactory.getUpdateQuestionsListService();
        iListQuestionsService = iCreateSurveyModelFactory.getListQuestionsService();

        ModelAndView mv = new ModelAndView("CreateSurvey/surveyhome");
        iCreateSurveyQuestionsModel = iUpdateQuestionsListService.removeQuestions(que);
        mv.addObject("publish", false);
        mv.addObject("surveyFlag", true);
        mv.addObject("courseID", courseID);
        mv.addObject("userID", userID);
        mv.addObject("questionsList", iListQuestionsService.listRepeatQuestions());
        mv.addObject("selectedQuestions", iCreateSurveyQuestionsModel.getSelectedQuestions());
        mv.addObject("selectedType", iCreateSurveyQuestionsModel.getSelectedTypes());
        return mv;
    }

    @RequestMapping("/save")
    public ModelAndView saveSurvey(@RequestParam(name = "id") long courseID, @RequestParam(name = "userID") long userID) {
        iCreateSurveyModelFactory = CreateSurveyModelFactory.FactorySingleton();
        iCreateSurveyQuestionsModel = iCreateSurveyModelFactory.createSurveyQuestionsModel();
        iCreateSurveyDBFactory = CreateSurveyDBFactory.FactorySingleton();
        iCreateSurveyDB = iCreateSurveyDBFactory.createSurveyDB();
        iListQuestionsService = iCreateSurveyModelFactory.getListQuestionsService();
        int status = 0;

        ModelAndView mv = new ModelAndView("CreateSurvey/surveyhome");
        mv.addObject("publish", true);
        mv.addObject("surveyFlag", true);
        mv.addObject("courseID", courseID);
        mv.addObject("userID", userID);
        mv.addObject("questionsList", iListQuestionsService.listRepeatQuestions());
        mv.addObject("selectedQuestions", iCreateSurveyQuestionsModel.getSelectedQuestions());
        mv.addObject("selectedType", iCreateSurveyQuestionsModel.getSelectedTypes());
        if (iCreateSurveyDB.saveSurvey(courseID, userID, status)) {
            mv.addObject("msgFlag", 0);
            mv.addObject("message", "Questions have been saved successfully, you can edit later else publish it now.");
        }
        return mv;
    }

    @RequestMapping("/publish")
    public ModelAndView publishSurvey(@RequestParam(name = "id") long courseID, @RequestParam(name = "userID") long userID) {
        iCreateSurveyDBFactory = CreateSurveyDBFactory.FactorySingleton();
        iCreateSurveyDB = iCreateSurveyDBFactory.createSurveyDB();

        int status = 1;
        ModelAndView mv = new ModelAndView("CreateSurvey/surveyhome");
        mv.addObject("courseID", courseID);
        mv.addObject("userID", userID);
        if (iCreateSurveyDB.saveSurvey(courseID, userID, status)) {
            mv.addObject("surveyFlag", false);
            mv.addObject("surveyMessage", "Questions have been published.");
        }
        return mv;
    }

    @RequestMapping("/unpublish")
    public ModelAndView unpublishSurvey(@RequestParam(name = "id") long courseID, @RequestParam(name = "userID") long userID) {
        iCreateSurveyModelFactory = CreateSurveyModelFactory.FactorySingleton();
        iCreateSurveyQuestionsModel = iCreateSurveyModelFactory.createSurveyQuestionsModel();
        iCreateSurveyDBFactory = CreateSurveyDBFactory.FactorySingleton();
        iCreateSurveyDB = iCreateSurveyDBFactory.createSurveyDB();
        iListQuestionsService = iCreateSurveyModelFactory.getListQuestionsService();

        ModelAndView mv = new ModelAndView("CreateSurvey/surveyhome");
        iCreateSurveyDB.updatePublishStatus(courseID);
        iCreateSurveyDB.fetchSavedQuestions(courseID);
        Dictionary hashMap = iListQuestionsService.listAllQuestionsforUser(userID);
        mv.addObject("surveyFlag", true);
        mv.addObject("courseID", courseID);
        mv.addObject("userID", userID);
        mv.addObject("questionsList", hashMap);
        mv.addObject("selectedQuestions", iCreateSurveyQuestionsModel.getSelectedQuestions());
        mv.addObject("publish", false);
        return mv;
    }

}

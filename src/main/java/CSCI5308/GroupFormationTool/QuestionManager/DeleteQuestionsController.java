package CSCI5308.GroupFormationTool.QuestionManager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DeleteQuestionsController {
	IQManagerDbFactory dbFactory = QManagerDbFactory.FactorySingleton();
	InterfaceDeleteQuestionsRepo interfaceDeleteQuestionsRepo = dbFactory.createDeleteQuestionRepo();
	InterfaceListQuestionsRepo interfaceListQuestionsRepo = dbFactory.createListQuestionsRepo();
	IQuestionsPersistence interfaceQuestionDB = dbFactory.createQuestionDB();
	InterfaceDeleteQuestionsModel interfaceDeleteQuestionsModel = QManagerModelFactory.FactorySingleton()
			.createDeleteQuestionsModel();

	@RequestMapping("/deletequestionspage")
	public ModelAndView deleteQuestions(Model model, @RequestParam(name = "userID") long userID) {
		ModelAndView mv = new ModelAndView("QuestionManager/deletequestions");
		mv.addObject("deleteQuestions", interfaceListQuestionsRepo.listQuestionsFromDB(userID));
		mv.addObject("message", "");
		mv.addObject("flag", true);
		model.addAttribute("userID", userID);
		return mv;
	}

	@RequestMapping("/checkResponse")
	public ModelAndView checkResponseBeforeDelete(Model model, DeleteQuestionsModel deleteQuestionsModel,
			@RequestParam(name = "userID") long userID) {
		this.interfaceDeleteQuestionsModel = deleteQuestionsModel;
		ModelAndView mv = new ModelAndView();
		model.addAttribute("userID", userID);
		mv.addObject("deleteQuestions", interfaceDeleteQuestionsModel);
		mv.addObject("userID", userID);
		mv.setViewName("QuestionManager/deletequestions");
		if (interfaceDeleteQuestionsRepo.checkIfResponsesExistInDB(userID,
				interfaceDeleteQuestionsModel.getSelectedQuestions())) {
			interfaceQuestionDB.deleteQuestionsFromDB(userID, interfaceDeleteQuestionsModel.getSelectedQuestions());
			mv.addObject("message", "Successfully deleted");
			return mv;
		}
		mv.addObject("message", "prompt");
		String StringList = "";
		for (String s : interfaceDeleteQuestionsModel.getSelectedQuestions()) {
			StringList += s + ",,";
		}
		mv.addObject("selectedQue", StringList);
		mv.addObject("flag", false);
		return mv;
	}

	@RequestMapping("/deletequestions")
	public ModelAndView deleteQuestions(Model model, @RequestParam(name = "selectedQue") String selectedQue,
			@RequestParam(name = "userID") long userID) {
		String[] chosenQuestions = selectedQue.split(",,");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("QuestionManager/deletequestions");

		if (interfaceQuestionDB.deleteQuestionsFromDB(userID, chosenQuestions)) {
			mv.addObject("message", "Successfully deleted");
			mv.addObject("flag", false);
		} else {
			mv.addObject("message", "Could not delete");
		}
		model.addAttribute("userID", userID);
		return mv;
	}

}

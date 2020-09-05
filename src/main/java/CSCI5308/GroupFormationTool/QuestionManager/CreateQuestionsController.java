package CSCI5308.GroupFormationTool.QuestionManager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CreateQuestionsController {
	InterfaceQuestionModel interfaceQuestionModel;
	IQuestionsPersistence interfaceQuestionDB = QManagerDbFactory.FactorySingleton().createQuestionDB();
	InterfaceResponses interfaceResponses = QManagerModelFactory.FactorySingleton().createResponses();
	private long userID;

	@RequestMapping("/questioneditor")
	public ModelAndView questionEditor(Model model, @RequestParam(name = "userID") long userID) {
		ModelAndView mv = new ModelAndView();
		interfaceQuestionModel = QManagerModelFactory.FactorySingleton().createQuestionModel();
		mv.addObject("questionModel", interfaceQuestionModel);
		this.userID = userID;
		mv.setViewName("QuestionManager/questioneditor");
		return mv;
	}

	@RequestMapping("/answereditor")
	public String answerEditor(QuestionModel questionModel, Model model) {
		this.interfaceQuestionModel = questionModel;
		this.interfaceQuestionModel.setUserID(this.userID);
		if (questionModel.getTypeSelect().equals("mcq1") || questionModel.getTypeSelect().equals("mcq2")) {
			model.addAttribute("question_type", true);
		}
		return "QuestionManager/answereditor";
	}

	@RequestMapping("/finish")
	public String finish(Responses response, Model model) {
		this.interfaceResponses = response;
		interfaceQuestionModel.createQuestion(interfaceQuestionDB);
		if (interfaceResponses.getResponse_txt() != null && interfaceResponses.getScore_txt() != null) {
			interfaceResponses.insertResponses(interfaceQuestionDB);
		}
		model.addAttribute("userID", this.userID);
		return "QuestionManager/finish";
	}
}

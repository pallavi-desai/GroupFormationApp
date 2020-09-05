package CSCI5308.GroupFormationTool.QuestionManager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
	@RequestMapping("/questionmanager")
	public String questionnaireAdmin(Model model, @RequestParam(name = "userID") long userID) {
		model.addAttribute("userID", userID);
		return "QuestionManager/QuestionManagerPage";
	}

	@RequestMapping("/welcomequestions")
	public String CreateQuestions(Model model, @RequestParam(name = "userID") long userID) {
		model.addAttribute("userID", userID);
		return "QuestionManager/welcome";
	}

	@RequestMapping("/listquestions")
	public String ListQuestions(Model model, @RequestParam(name = "userID") long userID) {
		model.addAttribute("userID", userID);
		return "QuestionManager/listquestions";
	}
}

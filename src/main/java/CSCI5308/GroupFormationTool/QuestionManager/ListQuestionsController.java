package CSCI5308.GroupFormationTool.QuestionManager;

import java.security.Principal;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListQuestionsController {
	IQManagerDbFactory dbFactory = QManagerDbFactory.FactorySingleton();
	IQuestionsPersistence interfaceQuestionDB = dbFactory.createQuestionDB();
	IQuestionSorters interfaceSortersDB = dbFactory.createSortingDB();
	InterfaceSorters interfaceSorters = QManagerModelFactory.FactorySingleton().createSorters();
	Map<String, String> sortingFields = interfaceSorters.sortingFieldList();
	Map<String, String> sortingOrders = interfaceSorters.sortingOrderList();
	String bannerID;
	private long userID;

	@RequestMapping(value = "/listquestions", method = RequestMethod.GET)
	public String showQuestions(ModelMap model, Principal principal, @RequestParam(name = "userID") long userID) {
		this.userID = userID;
		setModelForSorting(model);
		bannerID = principal.getName();
		model.addAttribute("questions", interfaceQuestionDB.loadAllQuestionsByID(bannerID));
		return "QuestionManager/listquestions";
	}

	@RequestMapping(value = "/listquestions", method = RequestMethod.POST, params = "action=sort")
	public ModelAndView performSort(@ModelAttribute("sorters") Sorters sorters, BindingResult result, ModelMap model) {
		this.interfaceSorters = sorters;
		setModelForSorting(model);
		model.addAttribute("questions", interfaceSortersDB.sort(bannerID, interfaceSorters));
		model.addAttribute("message", "Table is sorted by " + sortingFields.get(sorters.getSortField()) + " in "
				+ sortingOrders.get(sorters.getSortOrder()) + " Order");
		return new ModelAndView("QuestionManager/listquestions", model);
	}

	@RequestMapping(value = "/listquestions", method = RequestMethod.POST, params = "action=clearSort")
	public ModelAndView performClearSort(@ModelAttribute("sorters") Sorters sorters, BindingResult result,
			ModelMap model) {
		setModelForSorting(model);
		model.addAttribute("questions", interfaceSortersDB.clearSort(bannerID));
		return new ModelAndView("QuestionManager/listquestions", model);
	}

	public void setModelForSorting(ModelMap model) {
		model.addAttribute("userID", this.userID);
		model.addAttribute("sorters", interfaceSorters);
		model.addAttribute("sortingFields", sortingFields);
		model.addAttribute("sortingOrders", sortingOrders);
	}
}

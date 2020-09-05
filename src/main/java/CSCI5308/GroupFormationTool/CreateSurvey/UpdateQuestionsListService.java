package CSCI5308.GroupFormationTool.CreateSurvey;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UpdateQuestionsListService implements IUpdateQuestionsListService {

    ICreateSurveyModelFactory iCreateSurveyModelFactory;
    ICreateSurveyQuestionsModel iCreateSurveyQuestionsModel;

    @Override
    public ICreateSurveyQuestionsModel displayUpdatedQuestionList(String[] heading, String[] type, String que) {
        iCreateSurveyModelFactory = CreateSurveyModelFactory.FactorySingleton();
        iCreateSurveyQuestionsModel = iCreateSurveyModelFactory.createSurveyQuestionsModel();
        int index = 0;
        List<String> headingList = new ArrayList<>(Arrays.asList(heading));
        List<String> typeList = new ArrayList<>(Arrays.asList(type));
        List<String> selectedQue = new ArrayList<>();
        List<String> selectedType = new ArrayList<>();

        for (int i = 0; i < heading.length; i++) {
            if (heading[i].equals(que)) {
                index = i;
                break;
            }
        }
        if (iCreateSurveyQuestionsModel.getSelectedQuestions() != null) {
            List<String> sQue = (Arrays.asList(iCreateSurveyQuestionsModel.getSelectedQuestions()));
            List<String> sType = (Arrays.asList(iCreateSurveyQuestionsModel.getSelectedTypes()));
            selectedQue = new ArrayList<>(sQue);
            selectedType = new ArrayList<>(sType);
            selectedQue.add(headingList.get(index));
            selectedType.add(typeList.get(index));
            headingList.remove(index);
            typeList.remove(index);
        } else {
            selectedQue.add(headingList.get(index));
            selectedType.add(typeList.get(index));
            headingList.remove(index);
            typeList.remove(index);
        }
        return setDataInModel(headingList, typeList, selectedQue, selectedType);
    }

    private ICreateSurveyQuestionsModel setDataInModel(List<String> headingList, List<String> typeList,
                                                       List<String> selectedQue, List<String> selectedType) {
        iCreateSurveyModelFactory = CreateSurveyModelFactory.FactorySingleton();
        iCreateSurveyQuestionsModel = iCreateSurveyModelFactory.createSurveyQuestionsModel();
        iCreateSurveyQuestionsModel.setSelectedQuestions(selectedQue.toArray(new String[selectedQue.size()]));
        iCreateSurveyQuestionsModel.setSelectedTypes(selectedType.toArray(new String[selectedType.size()]));
        iCreateSurveyQuestionsModel.setQuestionHeading(headingList.toArray(new String[headingList.size()]));
        iCreateSurveyQuestionsModel.setQuestionType(typeList.toArray(new String[typeList.size()]));
        return iCreateSurveyQuestionsModel;
    }

    @Override
    public ICreateSurveyQuestionsModel removeQuestions(String que) {
        iCreateSurveyModelFactory = CreateSurveyModelFactory.FactorySingleton();
        iCreateSurveyQuestionsModel = iCreateSurveyModelFactory.createSurveyQuestionsModel();
        int index = 0;
        List<String> sQue = (Arrays.asList(iCreateSurveyQuestionsModel.getSelectedQuestions()));
        List<String> sType = (Arrays.asList(iCreateSurveyQuestionsModel.getSelectedTypes()));
        List<String> queHead = (Arrays.asList(iCreateSurveyQuestionsModel.getQuestionHeading()));
        List<String> queType = (Arrays.asList(iCreateSurveyQuestionsModel.getQuestionType()));
        sQue = new ArrayList<>(sQue);
        sType = new ArrayList<>(sType);
        queHead = new ArrayList<>(queHead);
        queType = new ArrayList<>(queType);

        for (int i = 0; i < sQue.size(); i++) {
            if (sQue.get(i).equals(que)) {
                index = i;
                break;
            }
        }
        queHead.add(sQue.get(index));
        queType.add(sType.get(index));
        sQue.remove(index);
        sType.remove(index);
        return setDataInModel(queHead, queType, sQue, sType);
    }

}

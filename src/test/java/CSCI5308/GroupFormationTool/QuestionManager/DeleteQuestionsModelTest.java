package CSCI5308.GroupFormationTool.QuestionManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

class DeleteQuestionsModelTest {


    @Test
    void getListQuestions() {
        String[] questions={"test"};
        InterfaceDeleteQuestionsModel interfaceDeleteQuestionsModel=QManagerModelFactory.FactorySingleton().createDeleteQuestionsModel();
        interfaceDeleteQuestionsModel.setListQuestions(questions);
        assertEquals(questions,interfaceDeleteQuestionsModel.getListQuestions());
    }

    @Test
    void setListQuestions() {
        String[] questions={"test"};
        InterfaceDeleteQuestionsModel interfaceDeleteQuestionsModel=QManagerModelFactory.FactorySingleton().createDeleteQuestionsModel();
        interfaceDeleteQuestionsModel.setListQuestions(questions);
        assertEquals(questions,interfaceDeleteQuestionsModel.getListQuestions());
    }

    @Test
    void getSelectedQuestions() {
        String[] questions={"question1"};
        InterfaceDeleteQuestionsModel interfaceDeleteQuestionsModel=QManagerModelFactory.FactorySingleton().createDeleteQuestionsModel();
        interfaceDeleteQuestionsModel.setSelectedQuestions(questions);
        assertSame(questions,interfaceDeleteQuestionsModel.getSelectedQuestions());
    }

    @Test
    void setSelectedQuestions() {
        String[] questions={"question1"};
        InterfaceDeleteQuestionsModel interfaceDeleteQuestionsModel=QManagerModelFactory.FactorySingleton().createDeleteQuestionsModel();
        interfaceDeleteQuestionsModel.setSelectedQuestions(questions);
        assertSame(questions,interfaceDeleteQuestionsModel.getSelectedQuestions());
    }
}
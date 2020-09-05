package CSCI5308.GroupFormationTool.QuestionManager;


import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

class QuestionModelTest {
    IQManagerModelFactory modelFactory = QManagerModelFactory.FactorySingleton();
    @Test
    void getTypeSelect() {
        InterfaceQuestionModel interfaceQuestionModel = modelFactory.createQuestionModel();
        interfaceQuestionModel.setTypeSelect("1");
        assertSame("1", interfaceQuestionModel.getTypeSelect());
    }

    @Test
    void setTypeSelect() {
        InterfaceQuestionModel interfaceQuestionModel = modelFactory.createQuestionModel();
        interfaceQuestionModel.setTypeSelect("1");
        assertSame("1", interfaceQuestionModel.getTypeSelect());
    }

    @Test
    void getQuestionTypeList() {
        HashMap<String,String> mock= new HashMap<>();
        mock.put("numeric","Numeric");
        InterfaceQuestionModel interfaceQuestionModel = modelFactory.createQuestionModel();
        interfaceQuestionModel.setQuestionTypeList(mock);
        assertSame(mock, interfaceQuestionModel.getQuestionTypeList());
    }

    @Test
    void setQuestionTypeList() {
        HashMap<String,String> mock= new HashMap<>();
        mock.put("numeric","Numeric");
        InterfaceQuestionModel interfaceQuestionModel = modelFactory.createQuestionModel();
        interfaceQuestionModel.setQuestionTypeList(mock);
        assertSame(mock, interfaceQuestionModel.getQuestionTypeList());
    }


    @Test
    void getQuestionTitle() {
        InterfaceQuestionModel interfaceQuestionModel = modelFactory.createQuestionModel();
        interfaceQuestionModel.setQuestionTitle("Course Credits");
        assertSame("Course Credits", interfaceQuestionModel.getQuestionTitle());
    }

    @Test
    void setQuestionTitle() {
        InterfaceQuestionModel interfaceQuestionModel = modelFactory.createQuestionModel();
        interfaceQuestionModel.setQuestionTitle("Course Credits");
        assertSame("Course Credits", interfaceQuestionModel.getQuestionTitle());
    }

    @Test
    void getQuestionText() {
        InterfaceQuestionModel interfaceQuestionModel = modelFactory.createQuestionModel();
        interfaceQuestionModel.setQuestionText("How many credit hours is the course?");
        assertSame("How many credit hours is the course?", interfaceQuestionModel.getQuestionText());
    }

    @Test
    void setQuestionText() {
        InterfaceQuestionModel interfaceQuestionModel = modelFactory.createQuestionModel();
        interfaceQuestionModel.setQuestionText("How many credit hours is the course?");
        assertSame("How many credit hours is the course?", interfaceQuestionModel.getQuestionText());
    }

    @Test
    void getResponseText() {
        InterfaceQuestionModel interfaceQuestionModel = modelFactory.createQuestionModel();
        interfaceQuestionModel.setResponseText("responsetext");
        assertSame("responsetext", interfaceQuestionModel.getResponseText());
    }

    @Test
    void setResponseText() {
        InterfaceQuestionModel interfaceQuestionModel = modelFactory.createQuestionModel();
        interfaceQuestionModel.setResponseText("responsetext");
        assertSame("responsetext", interfaceQuestionModel.getResponseText());
    }

    @Test
    void getResponseScore() {
        InterfaceQuestionModel interfaceQuestionModel = modelFactory.createQuestionModel();
        interfaceQuestionModel.setResponseScore(3);
        assertSame(3, interfaceQuestionModel.getResponseScore());
    }

    @Test
    void setResponseScore() {
        InterfaceQuestionModel interfaceQuestionModel = modelFactory.createQuestionModel();
        interfaceQuestionModel.setResponseScore(3);
        assertSame(3, interfaceQuestionModel.getResponseScore());
    }

    @Test
    void getQuestionID() {
        InterfaceQuestionModel interfaceQuestionModel = modelFactory.createQuestionModel();
        interfaceQuestionModel.setQuestionID((long)3);
        assertSame((long)3, interfaceQuestionModel.getQuestionID());
    }

    @Test
    void setQuestionID() {
        InterfaceQuestionModel interfaceQuestionModel = modelFactory.createQuestionModel();
        interfaceQuestionModel.setQuestionID((long)3);
        assertSame((long)3, interfaceQuestionModel.getQuestionID());
    }

    @Test
    void getQuestionType() {
        InterfaceQuestionModel interfaceQuestionModel = modelFactory.createQuestionModel();
        interfaceQuestionModel.setQuestionType("text");
        assertSame("text", interfaceQuestionModel.getQuestionType());
    }

    @Test
    void setQuestionType() {
        InterfaceQuestionModel interfaceQuestionModel = modelFactory.createQuestionModel();
        interfaceQuestionModel.setQuestionType("text");
        assertSame("text", interfaceQuestionModel.getQuestionType());
    }

    @Test
    void getUserID() {
        InterfaceQuestionModel interfaceQuestionModel = modelFactory.createQuestionModel();
        interfaceQuestionModel.setUserID((long) 101);
        assertSame((long) 101, interfaceQuestionModel.getUserID());
    }

    @Test
    void setUserID() {
        InterfaceQuestionModel interfaceQuestionModel = modelFactory.createQuestionModel();
        interfaceQuestionModel.setUserID((long) 101);
        assertSame((long) 101, interfaceQuestionModel.getUserID());
    }
}
package CSCI5308.GroupFormationTool.QuestionManager;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ResponsesTest {
    IQManagerModelFactory modelFactory = QManagerModelFactory.FactorySingleton();

    @Test
    void getResponse_txt() {
        InterfaceResponses interfaceResponses = modelFactory.createResponses();
        interfaceResponses.setResponse_txt("JAVA,PyTHON,JS,C,C++");
        assertTrue(interfaceResponses.getResponse_txt().equals("JAVA,PyTHON,JS,C,C++"));
    }

    @Test
    void setResponse_txt() {
        InterfaceResponses interfaceResponses = modelFactory.createResponses();
        interfaceResponses.setResponse_txt("JAVA,PyTHON,JS,C,C++");
        assertTrue(interfaceResponses.getResponse_txt().equals("JAVA,PyTHON,JS,C,C++"));
    }

    @Test
    void getScore_txt() {
        InterfaceResponses interfaceResponses = modelFactory.createResponses();
        interfaceResponses.setScore_txt("1,2,3,4");
        assertTrue(interfaceResponses.getScore_txt().equals("1,2,3,4"));
    }

    @Test
    void setScore_txt() {
        InterfaceResponses interfaceResponses = modelFactory.createResponses();
        interfaceResponses.setScore_txt("1,2,3,4");
        assertTrue(interfaceResponses.getScore_txt().equals("1,2,3,4"));
    }
}
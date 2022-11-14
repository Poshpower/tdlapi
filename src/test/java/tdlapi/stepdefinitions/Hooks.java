package tdlapi.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import tdlapi.helpers.TestCaseContext;

import static tdlapi.client.TrelloClient.changeBoardName;
import static tdlapi.client.TrelloClient.deleteList;
import static tdlapi.constant.ProjectConstants.BOARD_NAME;

public class Hooks {

    @Before
    public void beforeHook(Scenario scenario) {
       TestCaseContext.init();
        TestCaseContext.setScenario(scenario);
        System.out.println("the scenario has started");

    }

    @After
    public void afterHook() {
  changeBoardName(BOARD_NAME,TestCaseContext.getBoard().getId());
  deleteList(TestCaseContext.getList().getId());
  System.out.println("the scenario has Ended");

    }
}

package tdlapi.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.response.Response;
import tdlapi.domain.Board;
import org.assertj.core.api.Assertions;
import tdlapi.domain.List;
import tdlapi.helpers.TestCaseContext;


import static tdlapi.client.TrelloClient.changeBoardName;
import static tdlapi.client.TrelloClient.getBoardInfo;
import static tdlapi.client.TrelloClient.createList;
import static tdlapi.constant.ProjectConstants.BOARD_ID;
import static tdlapi.constant.ProjectConstants.BOARD_NAME;


public class TrelloSteps {

    @Given("The board exists and contains the correct information")
    public void getBoardAndCheckInfo(){
        Response response = getBoardInfo(BOARD_ID);
        Board board = response.as(Board.class);
        TestCaseContext.setBoard(board);

      Assertions.assertThat(board.getId())
              .as("we check that the board ID is equal")
              .isEqualTo(BOARD_ID);

        Assertions.assertThat(board.getName())
                .as("we check that the board name is equal")
                .isEqualTo(BOARD_NAME);
    }

    @When("I change the board to {string}")
    public void iChangeBoardTitle(String title){
       Response response = changeBoardName(title,TestCaseContext.getBoard().getId());
        Board board =response.as(Board.class);
        TestCaseContext.setBoard(board);
    }

    @And("I check that the board name was updated to {string}")
    public void iCheckUpdatedBoardTitleIsCorrect(String title){
        Assertions.assertThat(TestCaseContext.getBoard().getName())
                        .as("we check that board title was updated successfully")
                                .isEqualTo(title);
    }


    @Then("I add a list with title {string} to the board")
    public void iCreatedListWithTitle(String title){
        System.out.println(title);
        Response response = createList(title, TestCaseContext.getBoard().getId());
        List list = response.as(List.class);
        TestCaseContext.setList(list);

        Assertions.assertThat(TestCaseContext.getList().getName())
                .as("The list was created with the correct name")
                .isEqualTo(title);

        TestCaseContext.getScenario().log("The list id is " + list.getId());


}

    }

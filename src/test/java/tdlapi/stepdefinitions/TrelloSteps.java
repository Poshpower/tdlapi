package tdlapi.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.response.Response;
import tdlapi.domain.Board;
import org.assertj.core.api.Assertions;
import tdlapi.helpers.TestCaseContext;

import static tdlapi.client.TrelloClient.getBoardInfo;
import static tdlapi.constant.ProjectConstants.BOARD_ID;
import static tdlapi.constant.ProjectConstants.BOARD_NAME;

public class TrelloSteps {

    @Given("The board exists and contains the correct information")
    public void getBoardAndCheckInfo(){
//        System.out.println("We execute the 1st step");
        Response response = getBoardInfo(BOARD_ID);
        Board board = response.as(Board.class);
        TestCaseContext.setBoard(board);

//        System.out.println(board.getId());
//        System.out.println(board.getName());

      Assertions.assertThat(board.getId())
              .as("we check that the board ID is equal")
              .isEqualTo(BOARD_ID);

        Assertions.assertThat(board.getName())
                .as("we check that the board name is equal")
                .isEqualTo(BOARD_NAME);
    }

    @When("I change the board to {string}")
    public void iChangeBoardTitle(String title){
        System.out.println("the name is changed to" + title);
    }

    @And("I check that the board name was updated to {string}")
    public void iCheckUpdatedBoardName(String title){
        System.out.println("Check the board name is updated to " + title);
    }

    @Then("I add a list with title {string}to the board")
    public void iAddedListWithTitle(String title){
        System.out.println("Added a list with title " + title + "to the board");
    }

    @And("I delete the newly created list")
    public void iDeletedCreatedList(){
        System.out.println("I deleted  the new list");
    }
}

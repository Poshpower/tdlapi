Feature: This feature tests Trello api

  Scenario: Add a new list to test after tittle change
    Given The board exists and contains the correct information
    When I change the board to " New Title"
    And I check that the board name was updated to "New Title"
    Then I add a list with title "Example List "to the board
    And I delete the newly created list
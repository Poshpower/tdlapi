Feature: This feature tests Trello api

  Scenario: Add a new list to test after title change
    Given The board exists and contains the correct information
    When I change the board to "Sample Title"
    And I check that the board name was updated to "Sample Title"
    Then I add a list with title "Sample List" to the board

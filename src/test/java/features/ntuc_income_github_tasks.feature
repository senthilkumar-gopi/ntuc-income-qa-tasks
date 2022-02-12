@regression
Feature: NTUC income qa tasks - GitHub API and UI

  @TC_01
  Scenario: GitHub API tasks
    When Nikil get the Github User
    Then Nikil ensure the Response is successful with responsecode as 200
    And Nikil validates the Username, Name, Created on for the Github User
    When Nikil get all repository of the Github User
    Then Nikil ensure the Response is successful with responsecode as 200
    And Nikil validate the Stars and Releases of all Repositories

  @TC_02
  Scenario: GitHub UI tasks
    When Nikil opens the repository of the Github user
    Then Nikil verify the number of stars in UI with API

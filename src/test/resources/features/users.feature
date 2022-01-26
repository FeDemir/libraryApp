#ADD USER and verfiy with DB
Feature: User category
@wip @db
  Scenario: Add USER and verify with DB
    Given the user logged in as "librarian"
    And the user navigates to "Users" page
    Then the user adds user
    And the user fill related user information
    |James Bolt|
    |password|
    |email@mail.com|
    |Librarian|
    |ACTIVE|
    |2022-01-22|
    |2023-01-22|
    |123 Washington Dr. City, State, 12345|
    And the user checks this user is added DB

  @wip @db
  Scenario: Verify user information with db
    Given the user logged in as "librarian"
    And the user navigates to "Users" page
    When I open user "James Bolt"
    Then user information must match the database for "James Bolt"


  @wip @db
  Scenario: Update user information with db
    Given the user logged in as "librarian"
    And the user navigates to "Users" page
    When I open user "James Bolt"
    And the user updates user info "end_date" as "2100-01-01"

    #Then book information must match the database for "Clean Code"
    And the user information must be updated on database

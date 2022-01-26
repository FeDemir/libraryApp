
Feature: Book Category

  Scenario: verify book categories with UI
    Given the user logged in as "librarian"
    When the user navigates to "Books" page
    And the user gets all book categories in webpage
    Then user should be able to see following categories
      | Action and Adventure    |
      | Anthology               |
      | Classic                 |
      | Comic and Graphic Novel |
      | Crime and Detective     |
      | Drama                   |
      | Fable                   |
      | Fairy Tale              |
      | Fan-Fiction             |
      | Fantasy                 |
      | Historical Fiction      |
      | Horror                  |
      | Science Fiction         |
      | Biography/Autobiography |
      | Humor                   |
      | Romance                 |
      | Short Story             |
      | Essay                   |
      | Memoir                  |
      | Poetry                  |
  @db
  Scenario: verify book categories with DB
    Given the user logged in as "librarian"
    When the user navigates to "Books" page
    And the user gets all book categories in webpage
    Then verify book categories must match book categories table from db


  @wip @db
  Scenario: Verify book information with db
    Given the user logged in as "librarian"
    And the user navigates to "Books" page
    When I open book "Clean Code"
    Then book information must match the database for "Clean Code"


    #As a librarian
  @wip @db
  Scenario: Add BOOK and verify with DB
    Given the user logged in as "librarian"
    And the user navigates to "Books" page
    Then the user adds book
    And the user fill related book information
      |Empire|
      |2020|
      |978|
      |GWYNNE|
      |Historical Fiction|
      |no description|
    And the user checks this book is added DB





   #UPDATE BOOK and verify with DB
  @wip @db
  Scenario: Update book information with db
    Given the user logged in as "librarian"
    And the user navigates to "Books" page
    When I open book "Clean Code"
    And the user updates "description" as "this book is awesome"

    #Then book information must match the database for "Clean Code"
    And the book information must be updated on database



  
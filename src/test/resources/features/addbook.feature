@BG4-85
Feature: 

	#    Given the user logged in as "librarian"
	#    And the user navigates to "Books" page
	#    Then the user adds book
	#    And the user fill related book information
	#      |Empire|
	#      |2020|
	#      |978|
	#      |GWYNNE|
	#      |Historical Fiction|
	#      |no description|
	#    And the user checks this book is added DB
	@BG4-84 @db
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
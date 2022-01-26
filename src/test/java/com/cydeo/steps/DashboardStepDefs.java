package com.cydeo.steps;

import com.cydeo.pages.DashBoardPage;
import com.cydeo.pages.LoginPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class DashboardStepDefs
{
    String actualUserNumbers;
    String actualBookNumbers;
    String actualBorrowedBookNumbers;

    @Given("the user logged in as {string}")
    public void the_user_logged_in_as(String user) {
        new LoginPage().login(user);
        BrowserUtil.waitFor(4);
    }
    @When("user gets all information from modules")
    public void user_gets_all_information_from_modules() {
        DashBoardPage dashBoardPage=new DashBoardPage();

        actualUserNumbers = dashBoardPage.usersNumber.getText();
        System.out.println("actualUserNumbers = " + actualUserNumbers);
        actualBookNumbers = dashBoardPage.booksNumber.getText();
        System.out.println("actualBookNumbers = " + actualBookNumbers);
        actualBorrowedBookNumbers = dashBoardPage.borrowedBooksNumber.getText();
        System.out.println("actualBorrowedBookNumbers = " + actualBorrowedBookNumbers);

    }

    @Then("the informations should be same with database")
    public void the_informations_should_be_same_with_database() {


    // 1. get all infromation From UI
            // We already have t in previous step

    // Connect DB
     //   DB_Util.createConnection(); --> Since we create custom hooks no need to do this actions in there

    // 2. get all data from DB


        // this for USER Count

                //RUN QUERY
                DB_Util.runQuery("select count(*) from users");

                //Get related Data
                String expectedUserNumbers = DB_Util.getFirstRowFirstColumn();

                // make comparison
                Assert.assertEquals(expectedUserNumbers, actualUserNumbers);

        // this for BOOK Count

                // RUN QUERY
                DB_Util.runQuery("select count(*) from books");

                //Get me related Data book number
                String expectedBookNumber = DB_Util.getFirstRowFirstColumn();

                //make an assertion
                Assert.assertEquals(expectedBookNumber, actualBookNumbers);



        //Close Conn
       // DB_Util.destroy();    --> Since we create custom hooks no need to do this actions in there


    }

    String actualNumber;

    @When("user gets number of  {string}")
    public void userGetsNumberOf(String module) {

         actualNumber = new DashBoardPage().getModuleCount(module);
        System.out.println("actualNumber = " + actualNumber);

    }

    /*@Then("the informations should be same with database")
    public void theInformationsShouldBeSameWithDatabase() {

        //confirm number of users
        DB_Util.runQuery("select count(*) from users");
        String expectedUserNumbers = DB_Util.getFirstRowFirstColumn();
        Assert.assertEquals(expectedUserNumbers,actualUserNumbers);

        //confirm number of users
        DB_Util.runQuery("select count(*) from books");
        String expectedBookNumbers = DB_Util.getFirstRowFirstColumn();
        Assert.assertEquals(expectedBookNumbers,actualBookNumbers);

        //confirm number of users
        DB_Util.runQuery("select count(*) from book_borrow where is_returned=0;");
        String expectedBorrowedBookNumbers = DB_Util.getFirstRowFirstColumn();
        Assert.assertEquals(expectedBorrowedBookNumbers,actualBorrowedBookNumbers);


        
        
    }*/
}

package com.cydeo.steps;

import com.cydeo.pages.BookPage;
import com.cydeo.pages.DashBoardPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.DB_Util;
import com.sun.org.apache.bcel.internal.classfile.Code;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class BooksStepDefs {
    BookPage bookPage=new BookPage();
    List<String> actualCategoryList;





    @When("the user navigates to {string} page")
    public void the_user_navigates_to_page(String moduleName) {
        new DashBoardPage().navigateModule(moduleName);
    }


    @When("the user gets all book categories in webpage")
    public void the_user_gets_all_book_categories_in_webpage() {
        actualCategoryList=BrowserUtil.getAllSelectOptions(bookPage.mainCategoryElement);
        actualCategoryList.remove(0);
        System.out.println("expectedCategoryList = " + actualCategoryList);
    }

    @Then("user should be able to see following categories")
    public void user_should_be_able_to_see_following_categories(List<String> expectedCategoryList) {


        Assert.assertEquals(expectedCategoryList, actualCategoryList);

    }


    @When("I open book {string}")
    public void i_open_book(String bookName) {

        System.out.println("bookName = " + bookName);
        BrowserUtil.waitForClickablility(bookPage.search, 5).sendKeys(bookName);
        BrowserUtil.waitForClickablility(bookPage.editBook(bookName), 5).click();
        System.out.println("Book record found");
    }


    @Then("verify book categories must match book categories table from db")
    public void verifyBookCategoriesMustMatchBookCategoriesTableFromDb() {
        DB_Util.runQuery("select name from book_categories");
        List<String> expectedCategoryList = DB_Util.getColumnDataAsList(1);
                                                                
        Assert.assertEquals(expectedCategoryList,actualCategoryList);

    }


    @Then("book information must match the database for {string}")
    public void bookInformationMustMatchTheDatabaseFor(String bookName) {
        String actualBookName = bookPage.bookName.getAttribute("value");
        String actualBookISBN = bookPage.isbn.getAttribute("value");
        String actualBookYear = bookPage.year.getAttribute("value");
        String actualBookAuthor = bookPage.author.getAttribute("value");
        String actualBookCatName = bookPage.getCategoryName();
        String actualBookDesc = bookPage.description.getAttribute("value");
        System.out.println(actualBookName+"\t"+actualBookCatName);
        String query = "select books.name,isbn,year,author,book_categories.name,books.description from books inner join book_categories on books.book_category_id=book_categories.id where books.name='"+bookName+"'";
        DB_Util.runQuery(query);
        /*Map<String,String> dbData = DB_Util.getRowMap(1);
        System.out.println("dbData = " + dbData);


        String expBookName = bookName;
        String expBookYear =  dbData.get("year");
        String expBookAuthor =  dbData.get("author");
        String expBookDescription =  dbData.get("description");
        String expBookISBN =  dbData.get("isbn");
        String expBookCategory =  dbData.get("name");*/
        //System.out.println(DB_Util.getRowMap(1));
        List<String> dbDataList = DB_Util.getRowDataAsList(1);
        System.out.println("dbDataList = " + dbDataList);
        String expBookName = dbDataList.get(0);
        String expBookYear =  dbDataList.get(2);
        String expBookAuthor =  dbDataList.get(3);
        String expBookDescription =  dbDataList.get(5);
        String expBookISBN =  dbDataList.get(1);
        String expBookCategory =  dbDataList.get(4);


        //COMPARE
        Assert.assertEquals(expBookName,actualBookName);
        Assert.assertEquals(expBookISBN,actualBookISBN);
        Assert.assertEquals(expBookYear,actualBookYear);
        Assert.assertEquals(expBookAuthor,actualBookAuthor);
        Assert.assertEquals(expBookDescription,actualBookDesc);



    }

    @Then("the user adds book")
    public void theUserAddsBook() {
        bookPage.addBookBtn.click();
    }
    String newBookName;
    @And("the user fill related book information")
    public void theUserFillRelatedBookInformation(List<String> bookInfo) {
        System.out.println("bookInfo = " + bookInfo);
        bookPage.bookName.sendKeys(bookInfo.get(0));
        bookPage.isbn.sendKeys(bookInfo.get(1));
        bookPage.year.sendKeys(bookInfo.get(2));
        bookPage.author.sendKeys(bookInfo.get(3));
        bookPage.setCategory(bookInfo.get(4));
        bookPage.description.sendKeys(bookInfo.get(5));
        newBookName=bookInfo.get(0);
        bookPage.saveChangesBtn.click();
    }

    @And("the user checks this book is added DB")
    public void theUserChecksThisBookIsAddedDB() {
        bookInformationMustMatchTheDatabaseFor(newBookName);
    }

    @And("the user Fills the related field")
    public void theUserFillsTheRelatedField(List<String> update) {

    }

    @And("the book information must be updated on database")
    public void theBookInformationMustBeUpdatedOnDatabase() {
        bookInformationMustMatchTheDatabaseFor(newBookName);
    }

    @And("the user updates {string} as {string}")
    public void theUserUpdatesAs(String columnName, String data) {
        System.out.println("Records are updating for "+columnName+" as "+data);
        switch (columnName){
            case "name":bookPage.bookName.clear();
                bookPage.bookName.sendKeys(data);
                System.out.println("Book Name updated!");
                break;

            case "year":bookPage.year.clear();
                bookPage.year.sendKeys(data);
                System.out.println("Book year updated!");
                break;

            case "isbn":bookPage.isbn.clear();
                bookPage.isbn.sendKeys(data);
                System.out.println("Book isbn updated!");
                break;

            case "author":bookPage.author.clear();
                bookPage.author.sendKeys(data);
                System.out.println("Book author updated!");
                break;

            case "description":bookPage.description.clear();
                bookPage.description.sendKeys(data);
                System.out.println("Book description updated!");
                break;

            case "category":bookPage.setCategory(data);
                System.out.println("Book category name updated!");
                break;

        }
        newBookName=bookPage.bookName.getAttribute("value");
        bookPage.saveChangesBtn.click();
    }
}

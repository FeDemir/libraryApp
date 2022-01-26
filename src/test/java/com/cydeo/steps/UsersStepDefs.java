package com.cydeo.steps;

import com.cydeo.pages.UserPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class UsersStepDefs {
     UserPage userPage = new UserPage();

    @Then("the user adds user")
    public void the_user_adds_user() {
        // Write code here that turns the phrase above into concrete actions
        userPage.addUserBtn.click();
    }
    String newUserName;
    @Then("the user fill related user information")
    public void the_user_fill_related_user_information(List<String> userInfo) {
        userPage.userName.sendKeys(userInfo.get(0));
        userPage.userPass.sendKeys(userInfo.get(1));
        userPage.userEmail.sendKeys(userInfo.get(2));
        userPage.setUserGroup(userInfo.get(3));
        userPage.setStatus(userInfo.get(4));
        userPage.startDate.sendKeys(userInfo.get(5));
        userPage.endDate.sendKeys(userInfo.get(6));
        userPage.address.sendKeys(userInfo.get(7));
        newUserName = userPage.userName.getAttribute("value");
        userPage.saveChangesBtn.click();
    }
    @Then("the user checks this user is added DB")
    public void the_user_checks_this_user_is_added_db() {
        userInformationMustMatchTheDatabaseFor(newUserName);
    }

    @Then("user information must match the database for {string}")
    public void userInformationMustMatchTheDatabaseFor(String userName) {
        BrowserUtil.waitFor(5);
        String actUserName = userPage.userName.getAttribute("value");
        String actUserPass = userPage.userPass.getAttribute("value");
        String actUserEmail = userPage.userEmail.getAttribute("value");
        String actUserStartDate = userPage.startDate.getAttribute("value");
        String actUserEndDate = userPage.endDate.getAttribute("value");
        String actUserAddress = userPage.address.getAttribute("value");
        String actUserGroup = userPage.getUserGroup();
        System.out.println("actUserGroup = " + actUserGroup);
        String actUserStatus = userPage.getStatus();
        System.out.println("actUserStatus = " + actUserStatus);
        String query = "select full_name, password,email,g.group_name,status,start_date,end_date,address from users inner join user_groups g on users.user_group_id = g.id where full_name='"+userName+"'";
        DB_Util.runQuery(query);
        List<String> dbUserInfo = DB_Util.getRowDataAsList(1);
        System.out.println("dbUserInfo = " + dbUserInfo);

        //user info to variables
        String expUserName = dbUserInfo.get(0);
        String expUserPass = dbUserInfo.get(1);
        String expUserEmail = dbUserInfo.get(2);
        String expUserStartDate = dbUserInfo.get(5);
        String expUserEndDate = dbUserInfo.get(6);
        String expUserAddress = dbUserInfo.get(7);
        String expUserGroup = dbUserInfo.get(3);
        String expUserStatus = dbUserInfo.get(4);

        //COMPARE
        Assert.assertEquals(expUserName,actUserName);
        //Assert.assertEquals(expUserPass,actUserPass);//Password cannot compared because encrypted from database
        Assert.assertEquals(expUserEmail,actUserEmail);
        Assert.assertEquals(expUserGroup,actUserGroup);
        Assert.assertEquals(expUserStatus,actUserStatus);
        Assert.assertEquals(expUserStartDate,actUserStartDate);
        Assert.assertEquals(expUserEndDate,actUserEndDate);
        Assert.assertEquals(expUserAddress,actUserAddress);

    }

    @When("I open user {string}")
    public void iOpenUser(String userName) {
        System.out.println("userName = " + userName);
        BrowserUtil.waitForClickablility(userPage.search, 5).sendKeys(userName);
        BrowserUtil.waitFor(1);
        BrowserUtil.waitForClickablility(userPage.editUser(userName), 5).click();
        System.out.println("User record found");
    }

    @And("the user information must be updated on database")
    public void theUserInformationMustBeUpdatedOnDatabase() {
        userInformationMustMatchTheDatabaseFor(newUserName);
        
    }

    @And("the user updates user info {string} as {string}")
    public void theUserUpdatesUserInfoAs(String columnName, String data) {
        System.out.println("Records are updating for "+columnName+" as "+data);
        switch (columnName){
            case "full_name":userPage.userName.clear();
                userPage.userName.sendKeys(data);
                System.out.println("Full Name updated!");
                break;

            case "password":userPage.userPass.clear();
                userPage.userPass.sendKeys(data);
                System.out.println("User password updated!");
                break;

            case "email":userPage.userEmail.clear();
                userPage.userEmail.sendKeys(data);
                System.out.println("User email updated!");
                break;

            case "end_date":userPage.endDate.clear();
                userPage.endDate.sendKeys(data);
                System.out.println("User end date updated!");
                break;
            case "start_date":userPage.startDate.clear();
                userPage.startDate.sendKeys(data);
                System.out.println("User end date updated!");
                break;

            case "address":userPage.address.clear();
                userPage.address.sendKeys(data);
                System.out.println("User address updated!");
                break;

            case "status":
                userPage.setStatus(data);
                System.out.println("User status updated!");
                break;

            case "group_name":
                userPage.setUserGroup(data);
                System.out.println("User group updated!");
                break;

        }
        newUserName=userPage.userName.getAttribute("value");
        userPage.saveChangesBtn.click();
    }
}

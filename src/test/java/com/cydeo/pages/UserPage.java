package com.cydeo.pages;

import com.cydeo.utility.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class UserPage extends BasePage{
    @FindBy(xpath = "//table/tbody/tr")
    public List<WebElement> allRows;

    @FindBy(xpath = "//input[@type='search']")
    public WebElement search;

    @FindBy(id = "user_groups")
    public WebElement userGroups;

    @FindBy(name = "full_name")
    public WebElement userName;

    @FindBy(name = "password")
    public WebElement userPass;

    @FindBy(name = "email")
    public WebElement userEmail;

    @FindBy(name = "start_date")
    public WebElement startDate;

    @FindBy(name = "end_date")
    public WebElement endDate;

    @FindBy(name = "address")
    public WebElement address;

    @FindBy (xpath = "//div[@class='portlet-title']//a")
    public WebElement addUserBtn;

    @FindBy (xpath = "//button[@type='submit']")
    public WebElement saveChangesBtn;

    public WebElement editUser(String user) {
        String xpath = "//td[3][.='" + user + "']/../td/a";
        return Driver.getDriver().findElement(By.xpath(xpath));
    }

    public String getUserGroup(){
        Select categories = new Select(Driver.getDriver().findElement(By.id("user_group_id")));
        String i = categories.getFirstSelectedOption().getAttribute("value");
        WebElement optionText = Driver.getDriver().findElement(By.xpath("//option[@value='"+i+"']"));
        //System.out.println("optionText.getText() = " + optionText.getText());
        return optionText.getText();
    }

    
    public void setUserGroup(String group) {
        Select categories = new Select(Driver.getDriver().findElement(By.id("user_group_id")));
        
        categories.selectByVisibleText(group);
    }

    public String getStatus(){
        Select categories = new Select(Driver.getDriver().findElement(By.id("status")));
        String i = categories.getFirstSelectedOption().getAttribute("value");
        WebElement optionText = Driver.getDriver().findElement(By.xpath("//option[@value='"+i+"']"));
        //System.out.println("optionText.getText() = " + optionText.getText());
        return optionText.getText();
    }


    public void setStatus(String status) {
        Select categories = new Select(Driver.getDriver().findElement(By.id("status")));
        categories.selectByVisibleText(status);
    }
}

package pages;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomePage extends BasePage {
    String titleLocator = "com.slava.buylist:id/title";
    String editButtonLocator = "com.slava.buylist:id/imageView2";
    String deleteButtonLocator = "com.slava.buylist:id/imageView1";

    public HomePage (AndroidDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id="com.slava.buylist:id/editText1")
    public WebElement editBox;

    @AndroidFindBy(id="com.slava.buylist:id/button2")
    public WebElement addButton;

    @AndroidFindBy(id="com.slava.buylist:id/title")
    public WebElement listTitle;

    @AndroidFindBy(id = "com.slava.buylist:id/rr1")
    public List<AndroidElement> listOfLists;

    @AndroidFindBy(id = "com.slava.buylist:id/imageView2")
    AndroidElement editButton;

    @AndroidFindBy(id = "com.slava.buylist:id/imageView1")
    AndroidElement deleteButton;

    @AndroidFindBy(className = "android.widget.EditText")
    AndroidElement editNameBox;

    @AndroidFindBy(id = "android:id/button1")
    AndroidElement editBoxOkButton;

    @AndroidFindBy(id = "com.slava.buylist:id/button1")
    AndroidElement settingsButton;

    @Step("Type list name")
    public HomePage typeNameInEditBox(String name){
        editBox.sendKeys(name);
        return this;
    }

    @Step("Click add button")
    public HomePage clickAddButton(){
        addButton.click();
        return this;
    }


    @Step("Verify list name in MainActivity")
    public void verifyListName(String expectedName){
        Assert.assertEquals(listOfLists.get(listOfLists.size()-1).findElement(By.id(titleLocator)).getText(),expectedName);
    }

    @Step("Verify list name in MainActivity")
    public String getListName(){
        return listOfLists.get(listOfLists.size()-1).findElement(By.id(titleLocator)).getText();
    }

    @Step("Verify list name in MainActivity")
    public String getLastListText(){
       return listOfLists.get(listOfLists.size()-1).findElement(By.id(titleLocator)).getText();
    }

    @Step("Click listTitle")
    public HomePage clickListTitle(String listName){
        driver.findElementByAndroidUIAutomator("text(\""+listName+"\")").click();
        return this;
    }

    @Step("Rename list by number")
    public HomePage renameListByNumber(String newListName, int listNumber){
        listOfLists.get(listNumber).findElementById(editButtonLocator).click();
        editNameBox.sendKeys(newListName);
        editBoxOkButton.click();
        return this;
    }

    @Step("Click settings button and Add to My List")
    public HomePage addtoMyListFromHomePage(){
        settingsButton.click();
        driver.findElementByAndroidUIAutomator("text(\"My List\")").click();
        return this;
    }

    @Step("Delete list")
    public HomePage deleteListByNumber(int listNumber){
        listOfLists.get(listNumber).findElementById(deleteButtonLocator).click();
        editBoxOkButton.click();
        return this;
    }

    @Step("Verify that list is deleted")
    public void verifyListDeleted(String listName){
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        Assert.assertFalse(!driver.findElementsByAndroidUIAutomator("text(\""+listName+"\")").isEmpty());
    }

    @Step("Get false if list deleted deleted")
    public boolean isListExist(String listName){
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        return !driver.findElementsByAndroidUIAutomator("text(\""+listName+"\")").isEmpty();
    }

    @Override
    public HomePage pressBack(){
        return (HomePage) super.pressBack();
    }

    @Override
    public HomePage hideKeyboard(){
        return (HomePage) super.hideKeyboard();
    }

    public HomePage startMainActivity(){
        driver.startActivity(new Activity("com.slava.buylist", "MainActivity"));
        return this;

    }

    public int getListNumber(String listName){
        int num = 0;
        for(int i = 0;i<listOfLists.size();i++){
            if(!listOfLists.get(i).findElementsByAndroidUIAutomator("text(\""+listName+"\")").isEmpty()){
                num = i;
            }
        }
        return num;
    }


}

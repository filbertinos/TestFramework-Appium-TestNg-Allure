package pages;
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
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);

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
    WebElement editNameBox;

    @AndroidFindBy(id = "android:id/button1")
    AndroidElement editBoxOkButton;

    @AndroidFindBy(id = "com.slava.buylist:id/button1")
    AndroidElement settingsButton;

    @Step("Type list name")
    public void typeNameInEditBox(String name){
        editBox.sendKeys(name);
    }

    @Step("Click add button")
    public void clickAddButton(){
        addButton.click();
    }


    @Step("Verify list name in MainActivity")
    public void verifyListName(String expectedName){
        Assert.assertEquals(listOfLists.get(listOfLists.size()-1).findElement(By.id(titleLocator)).getText(),expectedName);
    }

    @Step("Click listTitle")
    public void clickListTitle(String listName){
        driver.findElementByAndroidUIAutomator("text(\""+listName+"\")").click();
    }

    @Step("Rename list by number")
    public void renameListByNumber(String newListName, int listNumber){
        listOfLists.get(listNumber).findElementById(editButtonLocator).click();
        editNameBox.sendKeys(newListName);
        editBoxOkButton.click();
    }

    @Step("Click settings button and Add to My List")
    public void addtoMyListFromHomePage(){
        settingsButton.click();
        driver.findElementByAndroidUIAutomator("text(\"My List\")").click();
    }

    @Step("Delete list")
    public void deleteListByNumber(int listNumber){
        listOfLists.get(listNumber).findElementById(deleteButtonLocator).click();
        editBoxOkButton.click();
    }

    @Step("Verify that list is deleted")
    public void verifyListDeleted(String listName){
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        Assert.assertFalse(!driver.findElementsByAndroidUIAutomator("text(\""+listName+"\")").isEmpty());
    }



}

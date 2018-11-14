package pages;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.LongPressOptions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static io.appium.java_client.touch.offset.ElementOption.element;

public class InnerListPage extends BasePage {

    String labelNameLocator = "com.slava.buylist:id/title";
    String labelPriceLocator = "com.slava.buylist:id/textView1";
    String labelAmountLocator = "com.slava.buylist:id/TextView01";
    String labelCommentLocator = "com.slava.buylist:id/str1";

    public InnerListPage(AndroidDriver driver){
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    @AndroidFindBy(id="com.slava.buylist:id/textView1")
    AndroidElement listTitle;

    @AndroidFindBy(id="com.slava.buylist:id/editText1")
    AndroidElement addProductBox;

    @AndroidFindBy(id="com.slava.buylist:id/button2")
    AndroidElement addProductButton;

    @AndroidFindBy(id="com.slava.buylist:id/editText2")
    AndroidElement priceBox;

    @AndroidFindBy(id="com.slava.buylist:id/editText3")
    AndroidElement amountBox;

    @AndroidFindBy(id="com.slava.buylist:id/rr1")
    List<AndroidElement> items;

    @AndroidFindBy(id="com.slava.buylist:id/editText4")
    AndroidElement commentBox;

    @AndroidFindBy(id="com.slava.buylist:id/spinner1")
    AndroidElement packageList;

    @AndroidFindBy(id="android:id/select_dialog_listview")
    AndroidElement selectDialog;

    @AndroidFindBy(id="com.slava.buylist:id/spinner2")
    AndroidElement listofCategories;

    @AndroidFindBy(id="com.slava.buylist:id/textView2")
    AndroidElement totalLabel;

    @AndroidFindBy(id="android:id/button1")
    AndroidElement deleteOkButton;

    @AndroidFindBy(id = "com.slava.buylist:id/button1")
    AndroidElement settingsButton;

    TouchAction ta = new TouchAction(driver);


    @Step("Verify list name in AddActivity")
    public void verifyListName (String expectedText) {
        Assert.assertEquals(listTitle.getText(), expectedText);
    }

    @Step("Click on add button")
    public void clickAddItemButton(){
        addProductButton.click();
    }

    @Step("Verify item name")
    public void verifyItemName(String expectedName){
        Assert.assertEquals(items.get(items.size()-1).findElement(By.id(labelNameLocator)).getText(),expectedName);
    }

    @Step("Verify item name")
    public void verifyItemNameByNumber(String expectedName, int itemNumber){
        Assert.assertEquals(items.get(itemNumber).findElement(By.id(labelNameLocator)).getText(),expectedName);
    }

    @Step("Verify item price")
    public void verifyItemPrice(String expectedPrice){
        String [] subStr = items.get(items.size()-1).findElement(By.id(labelPriceLocator)).getText().split(" ");
        Assert.assertEquals(subStr[0], expectedPrice);
    }


    @Step("Verify item amount")
    public void verifyItemAmount(String expectedAmount){
        String [] subStr = items.get(items.size()-1).findElement(By.id(labelAmountLocator)).getText().split(" ");
        Assert.assertEquals(subStr[0], expectedAmount);
    }

    @Step("Verify item package")
    public void verifyItemPackage(String expectedPackage){
        String [] subStr = items.get(items.size()-1).findElement(By.id(labelAmountLocator)).getText().split(" ");
        Assert.assertEquals(subStr[1], expectedPackage);
    }

    @Step("Verify item comment")
    public void verifyItemComment(String expectedComment){
        Assert.assertEquals(items.get(items.size()-1).findElement(By.id(labelCommentLocator)).getText(),expectedComment);

    }

    //TODO need improvement from dev side for successful check
    @Step("Verify item category")
    public void verifyItemCategory(){
        Assert.assertTrue(items.get(items.size()-1).findElement(By.xpath("//preceding-sibling::android.widget.ImageView")).isDisplayed());
    }

    @Step("Check total")
    public void verifyTotal(String expectedTotal){
        String [] subStr = totalLabel.getText().split(" ");
        Assert.assertEquals(subStr[1], expectedTotal);
    }

    @Step("Type item name")
    public void typeItemName(String name){
        addProductBox.sendKeys(name);
    }

    @Step("Type item price")
    public void typeItemPrice(String price){
        priceBox.sendKeys(price);
    }

    @Step("Type item amount")
    public void typeItemAmount(String amount){
        amountBox.sendKeys(amount);
    }

    @Step("Type item comment")
    public void typeItemComment(String comment){
        commentBox.sendKeys(comment);
    }

    @Step("Select {1} in {0}")
    public void selectBoxUse(String selectBox, String selectBoxItem){
        if(selectBox == "package"){
            packageList.click();
        }
        else if(selectBox == "category"){
            listofCategories.click();
        }
        selectDialog.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + selectBoxItem + "\"))").click();
    }

    @Step("Long press by name")
    public void longPressByName(String itemName){
        ta.longPress(LongPressOptions.longPressOptions().withElement(element(driver.findElementByAndroidUIAutomator("text(\""+itemName+"\")")))).perform();
    }

    @Step("Long press by number")
    public void longPressByNumber(Integer itemNumber){
        ta.longPress(LongPressOptions.longPressOptions().withElement(element(items.get(itemNumber)))).perform();
    }

    @Step("Edit item by name")
    public void editItemByName(String itemName){
        longPressByName(itemName);
        selectDialog.findElementByAndroidUIAutomator("text(\"Edit\")").click();
    }

    @Step("Edit item by number")
    public void editItembyNumber(Integer itemNumber){
        longPressByNumber(itemNumber);
        selectDialog.findElementByAndroidUIAutomator("text(\"Edit\")").click();
    }

    @Step("Delete item by name")
    public void deleteItemByName(String itemName){
        longPressByName(itemName);
        selectDialog.findElementByAndroidUIAutomator("text(\"Remove\")").click();
        deleteOkButton.click();
    }

    @Step("Delete item by number")
    public void deleteItemByNumber(Integer itemNumber){
        longPressByNumber(itemNumber);
        selectDialog.findElementByAndroidUIAutomator("text(\"Remove\")").click();
        deleteOkButton.click();
    }


    @Step("Verify by name that item does not exist")
    public void itemNotExistCheck(String itemName){
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        Assert.assertFalse(!driver.findElementsByAndroidUIAutomator("text(\""+itemName+"\")").isEmpty());
    }

    @Step("Copy item by name")
    public void copyItemByName(String itemName, String toList){
        longPressByName(itemName);
        selectDialog.findElementByAndroidUIAutomator("text(\"Copy\")").click();
        driver.findElementByAndroidUIAutomator("text(\""+toList+"\")").click();
    }

    @Step("Click setting button and Add From My List")
    public void addFromMyListInInnerPage(String itemName){
        settingsButton.click();
        driver.findElementByAndroidUIAutomator("text(\"Add from my list\")").click();
        driver.findElementByAndroidUIAutomator("text(\""+itemName+"\")").click();
        addProductButton.click();
    }
}

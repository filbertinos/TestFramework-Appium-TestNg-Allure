package pages;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.touch.LongPressOptions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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

    @AndroidFindBy(id="com.slava.buylist:id/item")
    List<AndroidElement> items;

    @AndroidFindBy(id="com.slava.buylist:id/textView1")
    List<AndroidElement> prices;

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

    private TouchAction ta = new TouchAction(driver);


    @Step("Verify list name in AddActivity")
    public void verifyListName (String expectedText) {
        Assert.assertEquals(listTitle.getText(), expectedText);
    }

    @Step("Click on add button")
    public InnerListPage clickAddItemButton(){
        addProductButton.click();
        return this;
    }

    @Step("Verify item name")
    public InnerListPage verifyItemName(String expectedName){
        Assert.assertEquals(items.get(items.size()-1).findElement(By.id(labelNameLocator)).getText(),expectedName);
        return this;
    }

    @Step("Get item name")
    public String getItemName(){
        return items.get(items.size()-1).findElement(By.id(labelNameLocator)).getText();
    }

    @Step("Get item name by item number")
    public String getItemNameByNumber(int number){
        return items.get(number).findElement(By.id(labelNameLocator)).getText();
    }

    //splitStringResultByItemNumber

    @Step("Get item info by name")
    public String getItemNameByName(String itemName){
        for (AndroidElement item:
             items) {
            if(item.findElementByAndroidUIAutomator("text(\"" + itemName + "\")").getText().equals(itemName)){
                return item.findElementById(labelNameLocator).getText();
            }

        }
        return "error";

    }

    @Step("Verify item price")
    public InnerListPage verifyItemPrice(String expectedPrice){
        String [] subStr = items.get(items.size()-1).findElement(By.id(labelPriceLocator)).getText().split(" ");
        Assert.assertEquals(subStr[0], expectedPrice);
        return this;
    }

    @Step("Get item price")
    public String getItemPrice(){
        return splitStringResult(labelPriceLocator,0);
    }

    @Step("Get item price by item name")
    public String getItemPriceByName(String itemName){
        return splitStringResultByItemNumber(labelPriceLocator,0, getItemNumber(itemName));
    }


    @Step("Verify item amount")
    public InnerListPage verifyItemAmount(String expectedAmount){
        String [] subStr = items.get(items.size()-1).findElement(By.id(labelAmountLocator)).getText().split(" ");
        Assert.assertEquals(subStr[0], expectedAmount);
        return this;
    }

//    @Step("Get item amount")
//    public double getItemAmount(){
//        //String [] subStr = items.get(items.size()-1).findElement(By.id(labelAmountLocator)).getText().split(" ");
//        return Double.parseDouble(splitStringResult(labelAmountLocator,0));
//    }

    @Step("Get item amount")
    public String getItemAmount(){
        //String [] subStr = items.get(items.size()-1).findElement(By.id(labelAmountLocator)).getText().split(" ");
        return splitStringResult(labelAmountLocator,0);
    }

    @Step("Get item amount by item name")
    public String getItemAmountByName(String itemnName){
        return splitStringResultByItemNumber(labelAmountLocator,0, getItemNumber(itemnName));
    }

    @Step("Verify item package")
    public InnerListPage verifyItemPackage(String expectedPackage){
        String [] subStr = items.get(items.size()-1).findElement(By.id(labelAmountLocator)).getText().split(" ");
        Assert.assertEquals(subStr[1], expectedPackage);
        return this;
    }

    @Step("Get item package")
    public String getItemPackage(){
        return splitStringResult(labelAmountLocator,1);
    }

    @Step("Get item package by item name")
    public String getItemPackageByName(String itemName){
        return splitStringResultByItemNumber(labelAmountLocator,1, getItemNumber(itemName));
    }



    @Step("Verify item comment")
    public InnerListPage verifyItemComment(String expectedComment){
        Assert.assertEquals(items.get(items.size()-1).findElement(By.id(labelCommentLocator)).getText(),expectedComment);
        return this;

    }

    @Step("Get item comment")
    public String getItemComment(){
        return items.get(items.size()-1).findElement(By.id(labelCommentLocator)).getText();
    }

    @Step("Get item comment by item number")
    public String getItemCommentByNumber(int number){
        return items.get(number).findElement(By.id(labelCommentLocator)).getText();
    }

    public String getItemInfo(String itemName, int number){
        return getItemNameByNumber(number)+","+getItemPriceByName(itemName)+","+getItemAmountByName(itemName)+","+getItemPackageByName(itemName)+","+getItemCommentByNumber(number);
    }


    @Step("Verify item name for MyList")
    public String getItemNameMyList(){
        return items.get(0).findElement(By.id(labelNameLocator)).getText();
    }

    @Step("Get item name for MyList")
    public void verifyItemNameMyList(String expectedName){
        Assert.assertEquals(items.get(0).findElement(By.id(labelNameLocator)).getText(),expectedName);
    }

    @Step("Verify item price for MyList")
    public void verifyItemPriceMyList(String expectedPrice){
        String [] subStr = items.get(0).findElement(By.id(labelPriceLocator)).getText().split(" ");
        Assert.assertEquals(subStr[0], expectedPrice);
    }


    @Step("Verify item amount for MyList")
    public void verifyItemAmountMyList(String expectedAmount){
        String [] subStr = items.get(0).findElement(By.id(labelAmountLocator)).getText().split(" ");
        Assert.assertEquals(subStr[0], expectedAmount);
    }

    @Step("Verify item package for MyList")
    public void verifyItemPackageMyList(String expectedPackage){
        String [] subStr = items.get(0).findElement(By.id(labelAmountLocator)).getText().split(" ");
        Assert.assertEquals(subStr[1], expectedPackage);
    }

    @Step("Verify item comment for MyList")
    public void verifyItemCommentMyList(String expectedComment){
        Assert.assertEquals(items.get(0).findElement(By.id(labelCommentLocator)).getText(),expectedComment);

    }

    //TODO need improvement from dev side for successful check
    @Step("Verify item category")
    public void verifyItemCategory(){
        Assert.assertTrue(items.get(items.size()-1).findElement(By.xpath("//preceding-sibling::android.widget.ImageView")).isDisplayed());
    }

    //Split string "Total: total currency" and give "total"
    @Step("Check total")
    public void verifyTotal(String expectedTotal){
        String [] subStr = totalLabel.getText().split(" ");
        Assert.assertEquals(subStr[1], expectedTotal);
    }

    @Step("Calculate total")
    public double calculateTotal() throws NoSuchElementException {
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        double sum = 0;
        double finalSum = 0;
        for (AndroidElement item : items) {
            if (item.findElementsById(labelPriceLocator).size() > 0 && item.findElementsById(labelAmountLocator).size() > 0) {
                String[] amount = item.findElementById(labelAmountLocator).getText().split(" ");
                String[] price = item.findElementById(labelPriceLocator).getText().split(" ");
                sum = (Double.parseDouble(price[0]) * (Double.parseDouble(amount[0])));
            } else if (item.findElementsById(labelPriceLocator).isEmpty() && item.findElementsById(labelAmountLocator).isEmpty()) {
                sum = 0;
            } else if (item.findElementsById(labelPriceLocator).size() > 0 && item.findElementsById(labelAmountLocator).isEmpty()) {
                String[] price = item.findElementById(labelPriceLocator).getText().split(" ");
                sum = Double.parseDouble(price[0]);
            } else if (item.findElementsById(labelPriceLocator).isEmpty() && item.findElementsById(labelAmountLocator).size() > 0) {
                sum = 0;
            }
            finalSum += sum;
        }

        return finalSum;
    }


    @Step("Get total")
    public double getTotal(){
        String [] subStr = totalLabel.getText().split(" ");
        return Double.parseDouble(subStr[1]);
    }

    @Step("Type item name")
    public InnerListPage typeItemName(String name){
        addProductBox.sendKeys(name);
        return this;
    }

    @Step("Type item price")
    public InnerListPage typeItemPrice(String price){
        if(price.equals("") ){

        }
        else{
            priceBox.sendKeys(price);
        }
        return this;
    }

    @Step("Type item amount")
    public InnerListPage typeItemAmount(String amount){
        if(amount.equals("")){

        }
        else{
            amountBox.sendKeys(amount);
        }
        return this;
        }

    @Step("Type item comment")
    public InnerListPage typeItemComment(String comment){
        if(comment.equals("")){

        }
        else{
            commentBox.sendKeys(comment);
        }
        return this;
    }

    @Step("Select item in SelectBox")
    public InnerListPage selectBoxUse(String selectBox, String selectBoxItem){
        if(selectBoxItem.equals("")){

        }
        else {
            if (selectBox.equals("package")) {
                packageList.click();
            } else if (selectBox.equals("category")) {
                listofCategories.click();
            }
            selectDialog.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + selectBoxItem + "\"))").click();
        }
        return this;
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
    public InnerListPage editItemByName(String itemName){
        longPressByName(itemName);
        selectDialog.findElementByAndroidUIAutomator("text(\"Edit\")").click();
        return this;
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
    public boolean itemExistCheck(String itemName){
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        return !driver.findElementsByAndroidUIAutomator("text(\""+itemName+"\")").isEmpty();
    }

    @Step("Copy item by name")
    public InnerListPage copyItemByName(String itemName, String toList){
        longPressByName(itemName);
        selectDialog.findElementByAndroidUIAutomator("text(\"Copy\")").click();
        driver.findElementByAndroidUIAutomator("text(\""+toList+"\")").click();
        return this;
    }

    @Step("Click setting button and Add From My List")
    public InnerListPage addFromMyListInInnerPage(String itemName){
        settingsButton.click();
        driver.findElementByAndroidUIAutomator("text(\"Add from my list\")").click();
        driver.findElementByAndroidUIAutomator("text(\""+itemName+"\")").click();
        addProductButton.click();
        return this;
    }

    public String splitStringResult(String locator, int index){
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        String[] subStr;
        if(items.get(items.size()-1).findElements(By.id(locator)).isEmpty()){
            return "";
        }
        else {
            subStr = items.get(items.size() - 1).findElement(By.id(locator)).getText().split(" ");
        }
        return subStr[index];
    }

    public int getItemNumber(String itemName){
        int num = 0;
       for(int i = 0;i<items.size();i++){
           if(!items.get(i).findElementsByAndroidUIAutomator("text(\""+itemName+"\")").isEmpty()){
               num = i;
           }
       }
       return num;
    }

    private String splitStringResultByItemNumber(String locator, int index, int number) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        try {
            String[] subStr = items.get(number).findElement(By.id(locator)).getText().split(" ");
            return subStr[index];
        }
        catch (org.openqa.selenium.NoSuchElementException e){
            return "";
        }
        }

        public void startAddActivity(){
            driver.startActivity(new Activity("com.slava.buylist", "ListAddActivity"));
        }


}

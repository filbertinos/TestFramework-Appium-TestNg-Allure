package tests;

import io.qameta.allure.*;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.InnerListPage;
import utils.Listeners.TestListener;

@Listeners({ TestListener.class })
@Epic("Regression Tests")
@Feature("Main functionality")
public class TestApp extends BaseTest {
    String item1Name = "Matches";
    String item2Name = "Apple";
    String item2Price = "3";
    String item3Name = "Sugar";
    String item3Price = "3";
    String item3Amount = "2";
    String item4Name = "Milk";
    String item4Price = "2";
    String item4Amount = "3";
    String item4Comment = "5% fat";
    String item4Package = "l";
    String item5Name = "Beer";
    String item5Price = "1.5";
    String item5Amount = "5";
    String item5Comment = "Light";
    String item5Package = "bottles";
    String item5Category = "Drinks, juices";
    String expectedTotal = "22.5";
    String item5NewAmount = "10";
    String item5NewPrice = "2.5";
    String item5NewComment = "Dark";
    String expectedTotalAfterChange = "40";
    String listName = "ShoppingList";
    String list2Name = "Mylist2";
    String list2NameNew = "MyList2";
    String currency = " £";
    String defPackage = " pcs.";

    @Test(priority = 0, description = "#1 Add new list and check its name")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Test Description: Check add new list functionality")
    //@Story("Invalid username and password login test")
    public void addNewListCheck() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        InnerListPage innerListPage = new InnerListPage(driver);
        homePage.typeNameInEditBox(listName);
        homePage.clickAddButton();
        innerListPage.verifyListName(listName);
        innerListPage.hideKeyboard();
        innerListPage.pressBack();
        homePage.verifyListName(listName);
        homePage.clickListTitle(listName);
    }

    @Test(priority = 1, dependsOnMethods = {"addNewListCheck"}, description = "#2 Add new item with name in the list")
    @Description("Test Description: Add new item with name and check that it exists in the list")
    public void addNewItemWithNameOnly() {
        InnerListPage innerListPage = new InnerListPage(driver);
        innerListPage.typeItemName(item1Name);
        innerListPage.clickAddItemButton();
        innerListPage.verifyItemName(item1Name);
    }

    @Test(priority = 2, dependsOnMethods = {"addNewListCheck"}, description = "#3 Add new item with name and price in the list")
    @Description("Test Description: Add new item with name and price and check that it exists in the list")
    public void addNewItemWithNameAndPrice() {
        InnerListPage innerListPage = new InnerListPage(driver);
        innerListPage.typeItemName(item2Name);
        innerListPage.typeItemPrice(item2Price);
        innerListPage.clickAddItemButton();
        innerListPage.verifyItemName(item2Name);
        innerListPage.verifyItemPrice(item2Price);
    }

    @Test(priority = 3, dependsOnMethods = {"addNewListCheck"}, description = "#4 Add new item with name, price and amount in the list")
    @Description("Test Description: Add new item with name, price and amount and check that it exists in the list")
    public void addNewItemWithNameAndPriceAndAmount() {
        InnerListPage innerListPage = new InnerListPage(driver);
        innerListPage.typeItemName(item3Name);
        innerListPage.typeItemPrice(item3Price);
        innerListPage.typeItemAmount(item3Amount);
        innerListPage.clickAddItemButton();
        innerListPage.verifyItemName(item3Name);
        innerListPage.verifyItemPrice(item3Price);
        innerListPage.verifyItemAmount(item3Amount);
    }

    @Test(priority = 4, dependsOnMethods = {"addNewListCheck"}, description = "#5 Add new item with name, price, amount, non default package and comment in the list")
    @Description("Test Description: Add new item with name, price, amount, comment and non default package and check that it exists in the list")
    public void addNewItemWithNameAndPriceAndAmountAndPackageAndComment() {
        InnerListPage innerListPage = new InnerListPage(driver);
        innerListPage.typeItemName(item4Name);
        innerListPage.typeItemPrice(item4Price);
        innerListPage.typeItemAmount(item4Amount);
        innerListPage.typeItemComment(item4Comment);
        innerListPage.selectBoxUse("package", item4Package);
        innerListPage.clickAddItemButton();
        innerListPage.verifyItemName(item4Name);
        innerListPage.verifyItemPrice(item4Price);
        innerListPage.verifyItemAmount(item4Amount);
        innerListPage.verifyItemComment(item4Comment);
        innerListPage.verifyItemPackage(item4Package);
    }

    @Test(priority = 5, dependsOnMethods = {"addNewListCheck"}, description = "#6 Add new item with name, price, amount, comment, category and non default in the list")
    @Description("Test Description: Add new item with name, price, amount, comment, category and non default package and check that it exists in the list")
    public void addNewItemWithNameAndPriceAndAmountAndPackageAndCommentAndCategory() {
        InnerListPage innerListPage = new InnerListPage(driver);
        innerListPage.typeItemName(item5Name);
        innerListPage.typeItemPrice(item5Price);
        innerListPage.typeItemAmount(item5Amount);
        innerListPage.typeItemComment(item5Comment);
        innerListPage.selectBoxUse("package", item5Package);
        innerListPage.selectBoxUse("category", item5Category);
        innerListPage.clickAddItemButton();
        innerListPage.verifyItemName(item5Name);
        innerListPage.verifyItemPrice(item5Price);
        innerListPage.verifyItemAmount(item5Amount);
        innerListPage.verifyItemComment(item5Comment);
        innerListPage.verifyItemPackage(item5Package);
        innerListPage.verifyItemCategory();
    }

    @Test(priority = 6, dependsOnMethods = {"addNewListCheck", "addNewItemWithNameOnly", "addNewItemWithNameAndPrice", "addNewItemWithNameAndPriceAndAmount", "addNewItemWithNameAndPriceAndAmountAndPackageAndComment", "addNewItemWithNameAndPriceAndAmountAndPackageAndCommentAndCategory"}, alwaysRun = true, description = "#7 Check total sum for items")
    @Description("Test Description: Check total price of items")
    public void checkTotal() {
        InnerListPage innerListPage = new InnerListPage(driver);
        innerListPage.verifyTotal(expectedTotal);
    }

    @Test(priority = 7, dependsOnMethods = {"addNewListCheck", "addNewItemWithNameAndPriceAndAmountAndPackageAndCommentAndCategory"}, description = "#8 Edit existing item")
    @Description("Test Description: Edit existing item in the list")
    public void editItem() {
        InnerListPage innerListPage = new InnerListPage(driver);
        innerListPage.editItemByName(item5Name);
        innerListPage.typeItemAmount(item5NewAmount);
        innerListPage.typeItemComment(item5NewComment);
        innerListPage.typeItemPrice(item5NewPrice);
        innerListPage.clickAddItemButton();
        innerListPage.verifyItemName(item5Name);
        innerListPage.verifyItemPrice(item5NewPrice);
        innerListPage.verifyItemAmount(item5NewAmount);
        innerListPage.verifyItemComment(item5NewComment);
        innerListPage.verifyItemPackage(item5Package);
    }

    @Test(priority = 8, dependsOnMethods = {"addNewListCheck", "addNewItemWithNameOnly", "addNewItemWithNameAndPrice", "addNewItemWithNameAndPriceAndAmount", "addNewItemWithNameAndPriceAndAmountAndPackageAndComment", "addNewItemWithNameAndPriceAndAmountAndPackageAndCommentAndCategory", "editItem"}, alwaysRun = true, description = "#9 Check total sum for items after editing item")
    @Description("Test Description: Check total price of items after editing item")
    public void checkTotalAfterEditItem() {
        InnerListPage innerListPage = new InnerListPage(driver);
        innerListPage.verifyTotal(expectedTotalAfterChange);
    }

    @Test(priority = 9, dependsOnMethods = {"addNewListCheck", "addNewItemWithNameAndPriceAndAmount"}, description = "#10 Delete existing item")
    @Description("Test Description: Delete existing item in the list")
    public void deleteItem() {
        InnerListPage innerListPage = new InnerListPage(driver);
        innerListPage.deleteItemByName(item3Name);
        innerListPage.itemNotExistCheck(item3Name);
    }


    @Test(priority = 10, description = "#11 Add second list")
    @Description("Test Description: Add second list")
    public void addSecondList() {
        String currentActivity = driver.currentActivity();
        HomePage homePage = new HomePage(driver);
        InnerListPage innerListPage = new InnerListPage(driver);
        if (currentActivity.equals(".MainActivity")) {
            homePage.typeNameInEditBox(list2Name);
            homePage.clickAddButton();
            innerListPage.verifyListName(list2Name);
            innerListPage.hideKeyboard();
            innerListPage.pressBack();
            homePage.verifyListName(list2Name);
        } else {
            innerListPage.pressBack();
            homePage.typeNameInEditBox(list2Name);
            homePage.clickAddButton();
            innerListPage.verifyListName(list2Name);
            innerListPage.hideKeyboard();
            innerListPage.pressBack();
            homePage.verifyListName(list2Name);
        }
    }

    @Test(priority = 11, dependsOnMethods = {"addNewListCheck", "addNewItemWithNameAndPriceAndAmountAndPackageAndCommentAndCategory", "editItem", "addSecondList"}, description = "#12 Copy item in new list")
    @Description("Test Description: Copy existing item in second list")
    public void copyItem() {
        HomePage homePage = new HomePage(driver);
        InnerListPage innerListPage = new InnerListPage(driver);
        homePage.clickListTitle(listName);
        innerListPage.copyItemByName(item5Name, list2Name);
        innerListPage.pressBack();
        homePage.clickListTitle(list2Name);
        innerListPage.verifyItemName(item5Name);
        innerListPage.verifyItemPrice(item5NewPrice);
        innerListPage.verifyItemAmount(item5NewAmount);
        innerListPage.verifyItemComment(item5NewComment);
        innerListPage.verifyItemPackage(item5Package);
    }


    @Test(priority = 12, description = "#13 Add item to MyList from Home Page")
    @Description("Test Description: Add item to MyList from Home Page")
    public void addItemToMyListFromHomePage() {
            HomePage homePage = new HomePage(driver);
        if (driver.currentActivity().equals(".MainActivity")) {
            homePage.addtoMyListFromHomePage();
            addNewItemWithNameAndPriceAndAmount();
        }
        else {
            homePage.pressBack();
            homePage.addtoMyListFromHomePage();
            addNewItemWithNameAndPriceAndAmount();
        }
    }

    @Test(priority = 13, dependsOnMethods = {"addSecondList","addItemToMyListFromHomePage"}, description = "#14 Add item from MyList")
    @Description("Test Description: Add item from MyList in Inner List Page")
    public void addItemFromMyList(){
            HomePage homePage = new HomePage(driver);
            InnerListPage innerListPage = new InnerListPage(driver);
            innerListPage.pressBack();
            homePage.clickListTitle(list2Name);
            innerListPage.addFromMyListInInnerPage(item3Name);
            innerListPage.verifyItemName(item3Name);
            innerListPage.verifyItemPrice(item3Price);
            innerListPage.verifyItemAmount(item3Amount);

    }

    @Test(priority = 14, dependsOnMethods = {"addSecondList"}, description = "#15 Rename existing list")
    @Description("Test Description: Rename existing list")
    public void renameList() {
        HomePage homePage = new HomePage(driver);
        homePage.pressBack();
        homePage.renameListByNumber(list2NameNew,1);
        homePage.verifyListName(list2Name + list2NameNew);
    }

    @Test(priority = 15, dependsOnMethods = {"addSecondList"}, description = "#16 Delete existing list")
    public void deleteList(){
        HomePage homePage = new HomePage(driver);
        homePage.deleteListByNumber(1);
        homePage.verifyListDeleted(list2Name + list2NameNew);
    }


}

package tests;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.Listeners.TestListener;
import utils.dataProviders.DataProviderClass;

@Listeners({TestListener.class})
@Epic("Regression Tests")
@Feature("Main functionality")
public class TestAppEndToEnd extends BaseTest {

    @BeforeGroups("items")
    public void clickList() {
        homePage.clickListTitle((String)DataProviderClass.dataProviderLists()[0][0]);
    }


    @BeforeGroups("mylist")
    public void clickAddmylist() {
        homePage.addtoMyListFromHomePage();
    }

    @AfterGroups("mylist")
    public void goToMainPage() {
        innerListPage.hideKeyboard().pressBack();
    }
////    @BeforeGroups("lists")
////    public void goToListsPage(){
////        homePage.hideKeyboard().pressBack();
////    }


    @Test(priority = 0, description = "Add new list and check its name", dataProvider = "data-provider-lists", dataProviderClass = DataProviderClass.class)
    @Severity(SeverityLevel.BLOCKER)
    @Description("Test Description: Check add new list functionality")
    public void addNewListCheck(String listName) {
        Assert.assertEquals(homePage.typeNameInEditBox(listName).clickAddButton().hideKeyboard().pressBack().getLastListText(), listName);
    }

    @Test(priority = 1, description = "Add item to MyList from Home Page", dataProvider = "data-provider-my-list", dataProviderClass = DataProviderClass.class, groups = {"mylist"})
    @Description("Test Description: Add item to MyList from Home Page")
    public void addItemToMyListFromHomePage(String name, String price, String amount, String pack, String comment, String category) {
        innerListPage.typeItemInfo(name, price, amount, pack, comment, category).clickAddItemButton();
        Assert.assertEquals(innerListPage.getItemInfo(name, innerListPage.getItemNumber(name)),name+","+price+","+amount+","+pack+","+comment);
    }

    @Test(priority = 2, dependsOnMethods = {"addNewListCheck"},  description = "Add new item with different number of parameters", dataProvider = "data-provider-items", dataProviderClass = DataProviderClass.class, groups = {"items"})
    @Description("Test Description: Add new item with different number of parameters")
    public void addNewItemWithDifferentNumberOfParameters(String name, String price, String amount, String pack, String comment, String category) {
        innerListPage.typeItemInfo(name, price, amount, pack, comment, category).clickAddItemButton();
        Assert.assertEquals(innerListPage.getItemInfo(name, innerListPage.getItemNumber(name)),name+","+price+","+amount+","+pack+","+comment);
    }


    @Test(priority = 3, dependsOnMethods = {"addNewListCheck", "addNewItemWithDifferentNumberOfParameters"}, alwaysRun = true, description = "Check total sum for items")
    @Description("Test Description: Check total price of items")
    public void checkTotal() {
        Assert.assertEquals(innerListPage.getTotal(), DataProviderClass.dataProviderTotal()[0][0]);

    }

    @Test(priority = 4, dependsOnMethods = {"addNewListCheck", "addNewItemWithDifferentNumberOfParameters"}, description = "Edit existing item", dataProvider = "data-provider-edit-items", dataProviderClass = DataProviderClass.class)
    @Description("Test Description: Edit existing item in the list")
    public void editItem(String itemName, String newItemName, String newPrice, String newAmount, String newPack, String newComment, String newCategory) {
        innerListPage.editItemByName(itemName).typeItemInfo(newItemName, newPrice, newAmount, newPack, newComment, newCategory).clickAddItemButton();
        Assert.assertEquals(innerListPage.getItemInfo(newItemName, innerListPage.getItemNumber(newItemName)),newItemName+","+newPrice+","+newAmount+","+newPack+","+newComment);
    }

    @Test(priority =5, dependsOnMethods = {"addNewListCheck", "addNewItemWithDifferentNumberOfParameters", "editItem"}, alwaysRun = true, description = "Check total sum for items after editing item")
    @Description("Test Description: Check total price of items after editing item")
    public void checkTotalAfterEditItem() {
        Assert.assertEquals(innerListPage.getTotal(), DataProviderClass.dataProviderTotal()[1][0]);
    }

    @Test(priority = 6, dependsOnMethods = {"addNewListCheck", "addNewItemWithDifferentNumberOfParameters"}, description = "Delete existing item", dataProvider = "data-provider-delete-items", dataProviderClass = DataProviderClass.class)
    @Description("Test Description: Delete existing item in the list")
    public void deleteItem(String itemName) {
        innerListPage.deleteItemByName(itemName);
        Assert.assertFalse(innerListPage.itemExistCheck(itemName));
    }


    @Test(priority = 7, dependsOnMethods = {"addNewListCheck", "addNewItemWithDifferentNumberOfParameters"}, description = "Copy item in new list", dataProvider = "data-provider-copy-items", dataProviderClass = DataProviderClass.class)
    @Description("Test Description: Copy existing item in another list")
    public void copyItem(String itemName, String toListName, String listName) {
        innerListPage.copyItemByName(itemName, toListName).pressBack();
        homePage.clickListTitle(toListName);
        Assert.assertEquals(innerListPage.getItemName(), itemName);
        innerListPage.hideKeyboard().pressBack();
        homePage.clickListTitle(listName);
    }


    @Test(priority = 8, description = "Add items from MyList", dataProvider = "data-provider-my-list", dataProviderClass = DataProviderClass.class)
    @Description("Test Description: Add item from MyList in Inner List Page")
    public void addItemFromMyList(String name, String price, String amount, String pack, String comment, String category) {
        innerListPage.addFromMyListInInnerPage(name);
        Assert.assertEquals(innerListPage.getItemInfo(name, innerListPage.getItemNumber(name)),name+","+price+","+amount+","+pack+","+comment);
    }

    @Test(priority = 9, description = "Rename list", dataProvider = "data-provider-main-activity", dataProviderClass = DataProviderClass.class)
    @Description("Test Description: Rename existing list")
    public void renameList(String name, String newName) {
        Assert.assertEquals(homePage.startMainActivity().typeNameInEditBox(name).clickAddButton().hideKeyboard().pressBack().renameListByNumber(newName, homePage.getListNumber(name)).getListName(), name + newName);
    }

    @Test(priority = 10, description = "Delete existing list", dataProvider = "data-provider-main-activity", dataProviderClass = DataProviderClass.class)
    @Description("Test Description: Delete existing list")
    public void deleteList(String name, String newName) {
        Assert.assertFalse(homePage.startMainActivity().typeNameInEditBox(newName).clickAddButton().hideKeyboard().pressBack().deleteListByNumber(homePage.getListNumber(newName)).isListExist(newName));
    }


}

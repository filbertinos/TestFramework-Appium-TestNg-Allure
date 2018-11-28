package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;
import utils.dataProviders.DataProviderClass;

public class TestAppIndependent extends BaseTest {
    @BeforeGroups("mylist")
    public void clickAddToMyList(){
        homePage.startMainActivity().typeNameInEditBox((String)DataProviderClass.dataProviderListsIndy()[0][0]).clickAddButton().hideKeyboard().pressBack();
    }

    @BeforeGroups("items")
    public void addList() {
        homePage.startMainActivity().typeNameInEditBox((String)DataProviderClass.dataProviderLists()[0][0]).clickAddButton();
    }

    @AfterGroups("items")
    public void checkTotal() {
        Assert.assertEquals(innerListPage.getTotal(), DataProviderClass.dataProviderTotal()[0][0]);
    }

    @BeforeGroups("items-edit")
    public void addListForEditItems() {
        homePage.startMainActivity().typeNameInEditBox((String)DataProviderClass.dataProviderListsIndy()[1][0]).clickAddButton();
    }

    @AfterGroups("items-edit")
    public void checkTotalAfterEdit() {
        Assert.assertEquals(innerListPage.getTotal(), DataProviderClass.dataProviderEditItemsIndy()[1][13]);
    }

    @BeforeGroups("items-delete")
    public void addListForDeleteItems() {
        homePage.startMainActivity().typeNameInEditBox((String)DataProviderClass.dataProviderListsIndy()[2][0]).clickAddButton();
    }

    @BeforeGroups("items-copy")
    public void addListsForCopyItems() {
        homePage.startMainActivity().typeNameInEditBox((String)DataProviderClass.dataProviderListsIndy()[3][0]).clickAddButton().hideKeyboard().pressBack();
        homePage.typeNameInEditBox((String)DataProviderClass.dataProviderListsIndy()[4][0]).clickAddButton().hideKeyboard().pressBack();
        homePage.typeNameInEditBox((String)DataProviderClass.dataProviderListsIndy()[5][0]).clickAddButton().hideKeyboard().pressBack().clickListTitle((String)DataProviderClass.dataProviderListsIndy()[3][0]);
    }

    @Test(priority = 0, description = "Add new list and check its name", dataProvider = "data-provider-lists", dataProviderClass = DataProviderClass.class)
    @Severity(SeverityLevel.BLOCKER)
    @Description("Test Description: Check add new list functionality")
    public void addNewListCheck(String listName) {
        Assert.assertEquals(homePage.typeNameInEditBox(listName).clickAddButton().hideKeyboard().pressBack().getLastListText(), listName);
    }

    @Test(priority = 1, description = "Rename list", dataProvider = "data-provider-main-activity", dataProviderClass = DataProviderClass.class)
    @Description("Test Description: Rename existing list")
    public void renameList(String name, String newName) {
        Assert.assertEquals(homePage.startMainActivity().typeNameInEditBox(name).clickAddButton().hideKeyboard().pressBack().renameListByNumber(newName,homePage.getListNumber(name)).getListName(),name+newName);
    }

    @Test(priority = 2, description = "Delete existing list", dataProvider = "data-provider-main-activity", dataProviderClass = DataProviderClass.class)
    @Description("Test Description: Delete existing list")
    public void deleteList(String name, String newName){
        Assert.assertFalse(homePage.startMainActivity().typeNameInEditBox(newName).clickAddButton().hideKeyboard().pressBack().deleteListByNumber(homePage.getListNumber(newName)).isListExist(newName));
    }

    @Test(priority = 3, description = "Add item to MyList from Home Page", dataProvider = "data-provider-my-list-independent", dataProviderClass = DataProviderClass.class, groups = {"mylist"})
    @Description("Test Description: Add item to MyList from Home Page")
    public void addItemToMyListAndAfterAddFromMyList(String name, String price, String amount, String pack, String comment, String category,String listName) {
        homePage.addtoMyListFromHomePage();
        innerListPage.typeItemName(name).typeItemPrice(price).typeItemAmount(amount).selectBoxUse("package", pack).typeItemComment(comment).selectBoxUse("category", category).clickAddItemButton();
        Assert.assertEquals(innerListPage.getItemInfo(name, innerListPage.getItemNumber(name)),name+","+price+","+amount+","+pack+","+comment);
        innerListPage.hideKeyboard().pressBack();
        homePage.clickListTitle(listName);
        innerListPage.addFromMyListInInnerPage(name);
        Assert.assertEquals(innerListPage.getItemInfo(name, innerListPage.getItemNumber(name)),name+","+price+","+amount+","+pack+","+comment);
        innerListPage.hideKeyboard().pressBack();
    }

    @Test(priority = 4,  description = "Add new item with different number of parameters", dataProvider = "data-provider-items", dataProviderClass = DataProviderClass.class, groups = {"items"})
    @Description("Test Description: Add new item with different number of parameters")
    public void addNewItemWithDifferentNumberOfParameters(String name, String price, String amount, String pack, String comment, String category) {
        innerListPage.typeItemName(name).typeItemPrice(price).typeItemAmount(amount).selectBoxUse("package", pack).typeItemComment(comment).selectBoxUse("category", category).clickAddItemButton();
        Assert.assertEquals(innerListPage.getItemInfo(name, innerListPage.getItemNumber(name)),name+","+price+","+amount+","+pack+","+comment);
    }

    @Test(priority = 5, description = "Edit existing item", dataProvider = "data-provider-edit-items-indy", dataProviderClass = DataProviderClass.class, groups = {"items-edit"})
    @Description("Test Description: Edit existing item in the list")
    public void editItem(String name, String price, String amount, String pack, String comment, String category, String newItemName, String newPrice, String newAmount, String newPack, String newComment, String newCategory, double total, double totalAfterEdit) {
        innerListPage.typeItemName(name).typeItemPrice(price).typeItemAmount(amount).selectBoxUse("package", pack).typeItemComment(comment).selectBoxUse("category", category).clickAddItemButton();
        Assert.assertEquals(innerListPage.getItemInfo(name, innerListPage.getItemNumber(name)),name+","+price+","+amount+","+pack+","+comment);
        Assert.assertEquals(innerListPage.getTotal(), total);
        innerListPage.editItemByName(name).typeItemName(newItemName).typeItemPrice(newPrice).typeItemAmount(newAmount).selectBoxUse("package", newPack).typeItemComment(newComment).selectBoxUse("category", newCategory).clickAddItemButton();
        Assert.assertEquals(innerListPage.getItemInfo(newItemName, innerListPage.getItemNumber(newItemName)),newItemName+","+newPrice+","+newAmount+","+newPack+","+newComment);
        Assert.assertEquals(innerListPage.getTotal(), totalAfterEdit);
    }

    @Test(priority = 6, description = "Delete existing item", dataProvider = "data-provider-delete-items-indy", dataProviderClass = DataProviderClass.class, groups = {"items-delete"})
    @Description("Test Description: Delete existing item in the list")
    public void deleteItem(String name) {
        innerListPage.typeItemName(name).clickAddItemButton();
        innerListPage.deleteItemByName(name);
        Assert.assertFalse(innerListPage.itemExistCheck(name));
    }

    @Test(priority = 7, description = "Copy item in new list", dataProvider = "data-provider-copy-items-indy", dataProviderClass = DataProviderClass.class, groups = {"items-copy"})
    @Description("Test Description: Copy existing item in another list")
    public void copyItem(String name, String price, String amount, String pack, String comment, String category, String toListName, String listName) {
        innerListPage.typeItemName(name).typeItemPrice(price).typeItemAmount(amount).selectBoxUse("package", pack).typeItemComment(comment).selectBoxUse("category", category).clickAddItemButton();
        innerListPage.copyItemByName(name, toListName).pressBack();
        homePage.clickListTitle(toListName);
        Assert.assertEquals(innerListPage.getItemName(), name);
        innerListPage.hideKeyboard().pressBack();
        homePage.clickListTitle(listName);
    }
}

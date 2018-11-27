package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;
import utils.dataProviders.DataProviderClass;

public class TestAppIndependent extends BaseTest {
    @BeforeGroups("mylist")
    public void clickAddToMyList(){
        homePage.startMainActivity().typeNameInEditBox("List").clickAddButton().hideKeyboard().pressBack().addtoMyListFromHomePage();
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
        homePage.startMainActivity();
        innerListPage.typeItemName(name).typeItemPrice(price).typeItemAmount(amount).selectBoxUse("package", pack).typeItemComment(comment).selectBoxUse("category", category).clickAddItemButton();
        Assert.assertEquals(innerListPage.getItemNameByNumber(innerListPage.getItemNumber(name)), name);
        Assert.assertEquals(innerListPage.getItemPriceByName(name), price);
        Assert.assertEquals(innerListPage.getItemAmountByName(name), amount);
        Assert.assertEquals(innerListPage.getItemPackageByName(name), pack);
        Assert.assertEquals(innerListPage.getItemCommentByNumber(innerListPage.getItemNumber(name)), comment);
        innerListPage.hideKeyboard().pressBack();
        homePage.clickListTitle(listName);
        innerListPage.addFromMyListInInnerPage(name);
        Assert.assertEquals(innerListPage.getItemNameByNumber(innerListPage.getItemNumber(name)), name);
        Assert.assertEquals(innerListPage.getItemPriceByName(name), price);
        Assert.assertEquals(innerListPage.getItemAmountByName(name), amount);
        Assert.assertEquals(innerListPage.getItemPackageByName(name), pack);
        Assert.assertEquals(innerListPage.getItemCommentByNumber(innerListPage.getItemNumber(name)), comment);
        innerListPage.hideKeyboard().pressBack();
    }
}

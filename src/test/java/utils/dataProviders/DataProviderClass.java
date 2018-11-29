package utils.dataProviders;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class DataProviderClass {

    @DataProvider(name = "data-provider-lists")
    public static Object[][] dataProviderLists()
    {
        return new Object[][] {
                { "ShoppingList"},
                { "Mylist2"},
                {"ThirdList"}
        };
    }

    @DataProvider(name = "data-provider-lists-indy")
    public static Object[][] dataProviderListsIndy()
    {
        return new Object[][] {
                { "List"},
                { "Fruits"},
                {"Trash"},
                {"FirstList"},
                {"SecondList"},
                {"List3"}
        };
    }

//    @DataProvider(name = "data-provider-items-old")
//    public static Object[][] dataProviderItemsOld(Method method)
//    {
//        Object[][] result = null;
//
//        switch (method.getName()) {
//            case "addNewItemWithNameOnly":
//                result = new Object[][]{
//                        {"Matches"},
//                };
//
//                break;
//            case "addNewItemWithNameAndPrice":
//                result = new Object[][]{
//                        {"Apple", 3}
//                };
//                break;
//            case "addNewItemWithNameAndPriceAndAmount":
//                result = new Object[][]{
//                        {"Sugar", 3, 2, "pcs."}
//                };
//                break;
//            case "addNewItemWithNameAndPriceAndAmountAndPackageAndComment":
//                result = new Object[][]{
//                        {"Milk", 2, 3, "l", "5% fat"}
//                };
//                break;
//        }
//
//        return result;
//
//    }

    @DataProvider(name = "data-provider-items")
    public static Object[][] dataProviderItems()
    {
        return new Object[][]{
                {"Matches", "", "", "", "", ""},
                {"Apples", "3", "", "", "", ""},
                {"Sugar", "3", "2", "pcs.", "", ""},
                {"Milk", "2", "3", "l", "5% fat", ""},
                {"Beer", "1.5", "5", "bottles", "Light", "Drinks, juices"},
       };
    }
    @DataProvider(name = "data-provider-edit-items")
    public static Object[][] dataProviderEditItems()
    {

        return new Object[][]{
                {"Beer","Beer", "10", "2.5", "bottles", "Dark", "Drinks, juices"},
                {"Apples","Macintosh", "3", "", "", "Green", "Fruits, vegetables, pickles"}
        };
    }

    @DataProvider(name = "data-provider-delete-items")
    public static Object[][] dataProviderDeleteItems()
    {

        return new Object[][]{
                {"Matches"}
        };
    }

    @DataProvider(name = "data-provider-delete-items-indy")
    public static Object[][] dataProviderAddItemAndDeleteItAfter()
    {

        return new Object[][]{
                {"Milk"},
                {"Juice"},
                {"Chocolate"}
        };
    }

    @DataProvider(name = "data-provider-copy-items")
    public static Object[][] dataProviderCopyItems()
    {
        return new Object[][]{
                {"Sugar","Mylist2","ShoppingList"},
                {"Milk", "ThirdList","ShoppingList"}
        };
    }

    @DataProvider(name = "data-provider-my-list")
    public static Object[][] dataProviderTyList()
    {

        return new Object[][]{
                {"Meat", "", "3", "kg.", "for barbecue", ""},
                {"Sauce", "", "", "", "for meat", ""}
        };
    }

    @DataProvider(name = "data-provider-my-list-independent")
    public static Object[][] dataProviderToList()
    {
        return new Object[][]{
                {"Meat", "", "3", "kg.", "for barbecue", "","List"},
                {"Sauce", "", "", "", "for meat", "","List"}
        };
    }

    @DataProvider(name = "data-provider-main-activity")
    public static Object[][] dataProviderMainScreen()
    {
        return new Object[][]{
                {"BuyLis", "BuyList"}
        };
    }

    @DataProvider(name = "data-provider-total")
    public static Object[][] dataProviderTotal()
    {

        return new Object[][] {
                {22.5},
                {40.0}
        };
    }

    @DataProvider(name = "data-provider-edit-items-indy")
    public static Object[][] dataProviderEditItemsIndy()
    {

        return new Object[][]{
                {"Orange", "2", "5", "kg.", "", "Fruits, vegetables, pickles", "Orange", "2", "3", "kg.", "", "Fruits, vegetables, pickles", 10.0, 6.0},
                {"Apples", "3", "", "", "", "", "Macintosh", "3", "", "", "Green", "Fruits, vegetables, pickles", 9.0, 9.0}
        };
    }

    @DataProvider(name = "data-provider-copy-items-indy")
    public static Object[][] dataProviderCopyItemsIndy()
    {

        return new Object[][]{
                {"Apples", "3", "", "", "", "","SecondList","FirstList"},
                {"Eggs", "", "22", "", "", "", "List3","FirstList"}
        };
    }

}

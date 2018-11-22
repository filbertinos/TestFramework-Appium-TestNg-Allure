package utils.dataProviders;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class DataProviderClass {

    @DataProvider(name = "data-provider-lists")
    public static Object[][] dataProviderLists()
    {
        Object[][] result = null;

        result = new Object[][] {
                { "ShoppingList"},
                { "Mylist2"},
                {"ThirdList"}
        };

        return result;
    }

    @DataProvider(name = "data-provider-items-old")
    public static Object[][] dataProviderItemsOld(Method method)
    {
        Object[][] result = null;

        switch (method.getName()) {
            case "addNewItemWithNameOnly":
                result = new Object[][]{
                        {"Matches"},
                };

                break;
            case "addNewItemWithNameAndPrice":
                result = new Object[][]{
                        {"Apple", 3}
                };
                break;
            case "addNewItemWithNameAndPriceAndAmount":
                result = new Object[][]{
                        {"Sugar", 3, 2, "pcs."}
                };
                break;
            case "addNewItemWithNameAndPriceAndAmountAndPackageAndComment":
                result = new Object[][]{
                        {"Milk", 2, 3, "l", "5% fat"}
                };
                break;
        }

        return result;

    }

    @DataProvider(name = "data-provider-items")
    public static Object[][] dataProviderItems()
    {

        Object[][] result;

        result = new Object[][]{
                {"Matches", "", "", "", "", ""},
                {"Apples", "3", "", "", "", ""},
                {"Sugar", "3", "2", "pcs.", "", ""},
                {"Milk", "2", "3", "l", "5% fat", ""},
                {"Beer", "1.5", "5", "bottles", "Light", "Drinks, juices"},
       };
        return result;
    }
    @DataProvider(name = "data-provider-edit-items")
    public static Object[][] dataProviderEditItems()
    {

        Object[][] result;

        result = new Object[][]{
                {"Beer","Beer", "10", "2.5", "bottles", "Dark", "Drinks, juices"},
                {"Apples","Macintosh", "", "", "", "Green", "Fruits, vegetables, pickles"}
        };
        return result;
    }

    @DataProvider(name = "data-provider-delete-items")
    public static Object[][] dataProviderDeleteItems()
    {

        Object[][] result;

        result = new Object[][]{
                {"Matches"}
        };
        return result;
    }

    @DataProvider(name = "data-provider-copy-items")
    public static Object[][] dataProviderCopyItems()
    {

        Object[][] result;

        result = new Object[][]{
                {"Sugar","Mylist2","ShoppingList"},
                {"Milk", "ThirdList","ShoppingList"}
        };
        return result;
    }

    @DataProvider(name = "data-provider-my-list")
    public static Object[][] dataProviderTyList()
    {

        Object[][] result;

        result = new Object[][]{
                {"Meat", "", "3", "kg.", "for barbecue", ""},
                {"Sauce", "", "", "", "for meat", ""}
        };
        return result;
    }
}

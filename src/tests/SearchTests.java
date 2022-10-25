package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Locale;

public class SearchTests extends CoreTestCase {
    @Test
    public void testSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();

        String search_line = "Linkin Park Diskography";

        SearchPageObject.typeSearchLine(search_line);
        int amountOfSearchResults = SearchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "We found too few results",
                amountOfSearchResults > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();

        String search_line = "sdfsdfsdfsdfsdfsdfdssdsdfsd";

        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    public void testCompareSearchFieldPlaceholderText() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String element_text = SearchPageObject.getSearchFieldText();

        assertEquals(
                "Text in search field doesn't much 'Search…'",
                "Search…",
                element_text
        );
    }

    @Test
    public void testCheckSearchResults() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        List<WebElement> search_results = SearchPageObject.getSearchResultsItems();

        assertTrue(
                "There are no multiple search results",
                search_results.size() > 1
        );

        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForSearchResultsItemsNotPresent();
    }

    @Test
    public void testVerifySearchResults() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");

        List<WebElement> search_results = SearchPageObject.getSearchResultsItemsTitles();

        for (WebElement element : search_results) {
            String element_text = element.getAttribute("text");
            String substring = "java";
            assertTrue(
                    "There is no 'Java' text in search result",
                    element_text.toLowerCase(Locale.ROOT).contains(substring)
            );
        }
    }
}

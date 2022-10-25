package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.openqa.selenium.By;

public class MyListPageObject extends MainPageObject {

    public static final String
            FOLDER_BY_NAME_TPL = "//*[@text='{FOLDER_NAME}']",
            ARTICLE_BY_TITLE_TPL = "//*[@text='{TITLE}']",
            ARTICLES_ITEMS_IN_LIST = "org.wikipedia:id/page_list_item_container",
            ARTICLES_IN_LIST_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']";

    /*TEMPLATES METHODS*/
    private static String getFolderXpathByName(String article_title) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", article_title);
    }

    private static String getSavedArticleXpathByTitle(String name_of_folder) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", name_of_folder);
    }

    private static String getArticleInListXpathBySubstring(String substring) {
        return ARTICLES_IN_LIST_TPL.replace("{SUBSTRING}", substring);
    }
    /*TEMPLATES METHODS*/

    public MyListPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void openFolderByName(String name_of_folder) {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                By.xpath(folder_name_xpath),
                "Cannot find folder by name " + name_of_folder,
                15
        );
    }

    public void waitForArticleToAppearByTitle(String article_title) {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(
                By.xpath(article_xpath),
                "Cannot finad article by title " + article_title,
                15
        );
    }

    public void waitForArticleToDisappearByTitle(String article_title) {
        String article_xpath = getSavedArticleXpathByTitle(article_title);

        this.waitForElementNotPresent(
                By.xpath(article_xpath),
                "Saved article still present with title " + article_title,
                15
        );
    }

    public void swipeByArticleToDelete(String article_title) {
        this.waitForArticleToAppearByTitle(article_title);

        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.swipeElementToLEft(
                By.xpath(article_xpath),
                "Cannot find saved article"
        );

        this.waitForArticleToDisappearByTitle(article_title);
    }

    public int getAmountOfArticlesInList() {
        return this.getAmountOfElements(By.id(ARTICLES_ITEMS_IN_LIST));
    }

    public void openArticleFromListBySubstring(String substring) {
        String articleLocator = getArticleInListXpathBySubstring(substring);

        this.waitForElementAndClick(
                By.xpath(articleLocator),
                "Cannot find 'general-purpose programming language' topic searching by Python",
                15
        );
    }
}

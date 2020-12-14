package ru.ifmo.se.lab3;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ifmo.se.lab3.pages.MainPage;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class SearchTest extends BaseTest {
    @Before
    public void signInBefore() {
        this.signIn();
    }

    @Test
    public void testSubscriptionCreation() {
        var mainPage = new MainPage(driver);
        mainPage.open();
        var wait = new WebDriverWait(driver, 5L);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='sourceUrl']")));

        var search = mainPage.search("http://feeds.feedburner.com/feedburner/Ywlxc");

        search.pressNext();

        search.pressNext();

        search.pressNext();

        search.waitForElement(By.xpath("//div[@class='confirm message']"));
    }

    @Test
    public void testErrorOnShortSearchText() {
        var mainPage = new MainPage(driver);
        mainPage.open();

        var search = mainPage.search("https://podcasts.google.com/feed/aHR0cHM6Ly93ZWItc3RhbmRhcmRzLnJ1L3BvZGNhc3QvZmVlZC8?sa=X&ved=2ahUKEwi36LTEmcztAhUG_4UKHeXwDcEQ9sEGegQIARAC");
        search.waitForElement(By.xpath("//div[@class='error message']"));
    }
}

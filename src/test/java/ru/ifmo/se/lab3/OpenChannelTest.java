package ru.ifmo.se.lab3;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ifmo.se.lab3.pages.MainPage;

public class OpenChannelTest extends BaseTest {
    @Before
    public void setPostFragment() {
        this.signIn();
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
    public void testChannel() {
        var mainPage = new MainPage(driver);
        mainPage.open();

        var wait = new WebDriverWait(driver, 5L);
        var xpathInstance = By.xpath("//a[@title='Jump to the Analyze tab for this feed']");
        wait.until(ExpectedConditions.elementToBeClickable(xpathInstance));
        driver.findElement(xpathInstance).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[text()='AdSense for Feeds and FeedBurner Blog']")));
    }
}

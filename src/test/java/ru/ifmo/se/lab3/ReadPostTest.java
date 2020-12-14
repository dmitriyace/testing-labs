package ru.ifmo.se.lab3;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import ru.ifmo.se.lab3.pages.MainPage;
import ru.ifmo.se.lab3.pages.fragments.PostFragment;

public class ReadPostTest extends BaseTest {
    private PostFragment postFragment;

    @Before
    public void setPostFragment() {
        this.signIn();
        var mainPage = new MainPage(driver);
        postFragment = mainPage.openPost();

    }

    @Test
    public void testPreview() {
        postFragment.waitForElement(By.xpath("//a[text()='Saying goodbye to the AdSense for Feeds blog']"));
    }
}

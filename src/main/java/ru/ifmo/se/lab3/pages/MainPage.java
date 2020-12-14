package ru.ifmo.se.lab3.pages;

import org.openqa.selenium.*;
import ru.ifmo.se.lab3.pages.fragments.PostFragment;
import ru.ifmo.se.lab3.pages.fragments.SearchFragment;
import ru.ifmo.se.lab3.pages.fragments.SignInFragment;

public class MainPage extends AbstractPage {

    public MainPage(WebDriver driver) {
        super(driver, "/");
    }

    public SearchFragment search(String text) {
        var input = driver.findElement(By.xpath("//input[@name='sourceUrl']"));
        input.sendKeys(text, Keys.ENTER);
        return new SearchFragment(driver);
    }

    public SignInFragment goToSignIn() {
        return new SignInFragment(driver);
    }

    public PostFragment openPost() {
        var linkToPost = driver.findElement(By.xpath("//a[text()='Saying goodbye to the AdSense for Feeds blog']"));
        linkToPost.click();
        return new PostFragment(driver);
    }
}

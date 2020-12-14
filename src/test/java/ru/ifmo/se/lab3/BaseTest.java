package ru.ifmo.se.lab3;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.ifmo.se.lab3.pages.MainPage;
import ru.ifmo.se.lab3.pages.fragments.SignInFragment;

import static org.junit.Assert.assertTrue;

public class BaseTest {
    private static final String FIREFOX_DRIVER = "firefox";

    protected WebDriver driver;

    @Before
    public void createDriver() {
        var property = System.getProperty("lab3.driver");
        if (FIREFOX_DRIVER.equals(property)) {
            driver = DriverFactory.createDriver(DriverType.FIREFOX);
        } else {
            driver = DriverFactory.createDriver(DriverType.CHROME);
        }
    }

    public void signIn() {
        SignInFragment signInFragment;

        var mainPage = new MainPage(driver);

        mainPage.open();

        signInFragment = mainPage.goToSignIn();

        assertTrue(signInFragment.isLoginPresence());

        this.driver.get("https://accounts.google.com/o/oauth2/v2/auth/oauthchooseaccount?redirect_uri=https%3A%2F%2Fdevelopers.google.com%2Foauthplayground&prompt=consent&response_type=code&client_id=407408718192.apps.googleusercontent.com&scope=email&access_type=offline&flowName=GeneralOAuthFlow");

        signInFragment.signInClick("dimonace98@gmail.com");

        signInFragment.waitForElement(By.xpath("//input[@name='password']"));

        signInFragment.passwordEnter("021198lbvf");

        signInFragment.waitForElement(By.xpath("//span[text()='Playground']"));

        this.driver.get("http://feedburner.google.com/");

        signInFragment.waitForElement(By.xpath("//div[@class='confirm message']"));
    }

    @After
    public void quitDriver() {
        driver.quit();
    }
}

package ru.ifmo.se.lab3;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import ru.ifmo.se.lab3.pages.MainPage;
import ru.ifmo.se.lab3.pages.fragments.SignInFragment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SignInTest extends BaseTest {
    private SignInFragment signInFragment;

    @Before
    public void setSignInFragment() {
        var mainPage = new MainPage(driver);
        mainPage.open();
        signInFragment = mainPage.goToSignIn();
    }

    @Test
    public void testSignInElements() {
        assertTrue(signInFragment.isLoginPresence());
        assertTrue(signInFragment.isCaptchaPresence());
    }

    @Test
    public void testErrorOnInsecureBrowser() {
        assertTrue(signInFragment.isLoginPresence());

        signInFragment.signInClick("chistohodovda@gmail.com");

        signInFragment.waitForElement(By.xpath("//span[text()='Не удалось войти в аккаунт']"));
    }

    @Test
    public void testSignInCorrect() {
        assertTrue(signInFragment.isLoginPresence());

        this.driver.get("https://accounts.google.com/o/oauth2/v2/auth/oauthchooseaccount?redirect_uri=https%3A%2F%2Fdevelopers.google.com%2Foauthplayground&prompt=consent&response_type=code&client_id=407408718192.apps.googleusercontent.com&scope=email&access_type=offline&flowName=GeneralOAuthFlow");

        signInFragment.signInClick("dimonace98@gmail.com");

        signInFragment.waitForElement(By.xpath("//input[@name='password']"));

        signInFragment.passwordEnter("021198lbvf");

        signInFragment.waitForElement(By.xpath("//span[text()='Playground']"));

        this.driver.get("http://feedburner.google.com/");

        signInFragment.waitForElement(By.xpath("//div[@class='confirm message']"));
    }
}

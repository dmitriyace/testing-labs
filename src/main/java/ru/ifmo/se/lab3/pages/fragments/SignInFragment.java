package ru.ifmo.se.lab3.pages.fragments;

import org.openqa.selenium.*;

public class SignInFragment extends AbstractFragment {
    private final static String TITLE = "Google FeedBurner";

    private final static By LOGIN_INPUT_XPATH = By.xpath("//input[@type='email']");
    private final static By PASSWORD_INPUT_XPATH = By.xpath("//input[@name='password']");
    private final static By CAPTCHA_XPATH = By.xpath("//script[@id='base-js']");

    public SignInFragment(WebDriver driver) {
        super(driver);
        waitForTitle(TITLE);
    }

    public boolean isLoginPresence() {
        return isElementPresence(LOGIN_INPUT_XPATH);
    }

    public boolean isPasswordPresence() {
        return isElementPresence(PASSWORD_INPUT_XPATH);
    }

    public boolean isCaptchaPresence() {
        return isElementPresence(CAPTCHA_XPATH);
    }

    public void signInClick(String login) {
        var loginElem = driver.findElement(LOGIN_INPUT_XPATH);

        loginElem.sendKeys(login, Keys.ENTER);
    }

    public void passwordEnter(String password) {
        var passwordElem = driver.findElement(PASSWORD_INPUT_XPATH);
        passwordElem.sendKeys(password, Keys.ENTER);
    }

    public String getErrorText() {
        var errorElem = driver.findElement(By.xpath("//span[text()='Не удалось войти в аккаунт']"));
        return errorElem.getText();
    }

    private boolean isElementPresence(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException exc) {
            return false;
        }
    }

}

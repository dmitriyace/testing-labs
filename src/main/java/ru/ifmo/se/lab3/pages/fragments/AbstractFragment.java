package ru.ifmo.se.lab3.pages.fragments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractFragment {
    private final static Long DEFAULT_TIMEOUT_SECONDS = 10L;

    protected final WebDriver driver;

    public AbstractFragment(WebDriver driver) {
        this.driver = driver;
    }

    WebElement waitFor(By by) {
        return waitFor(by, DEFAULT_TIMEOUT_SECONDS);
    }

    WebElement waitFor(By by, long timeout) {
        var wait = new WebDriverWait(driver, timeout);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    boolean waitForTitle(String title) {
        return waitForTitle(title, DEFAULT_TIMEOUT_SECONDS);
    }

    boolean waitForTitle(String title, long timeout) {
        var wait = new WebDriverWait(driver, timeout);
        return wait.until(ExpectedConditions.titleContains(title));
    }

    public WebElement waitForElement(By title) {
        var wait = new WebDriverWait(driver, 5L);
        return wait.until(ExpectedConditions.elementToBeClickable(title));
    }
}

package ru.ifmo.se.lab3.pages.fragments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchFragment extends AbstractFragment {

    public SearchFragment(WebDriver driver) {
        super(driver);
    }

    public void pressNext() {
        waitFor(By.xpath("//input[@value='Next »']"));
        driver.findElement(By.xpath("//input[@value='Next »']")).click();
    }
}

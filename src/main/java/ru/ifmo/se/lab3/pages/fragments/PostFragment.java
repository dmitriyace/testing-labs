package ru.ifmo.se.lab3.pages.fragments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PostFragment extends AbstractFragment {
    private static final By PREVIEW_TEXT_XPATH = By.xpath("//div[@class='tableborder']/div[@class='row1']/div[1]");

    public PostFragment(WebDriver driver) {
        super(driver);
    }
}

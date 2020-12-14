package ru.ifmo.se.lab3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Arrays;
import java.util.Optional;

public class DriverFactory {
    private static final String CHROME_DRIVER = "chromedriver";
    private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";

    private static final String FIREFOX_DRIVER = "geckodriver";
    private static final String FIREFOX_DRIVER_PROPERTY = "webdriver.gecko.driver";


    public static WebDriver createDriver(DriverType type) {
        switch (type) {
            case CHROME:
                return getChromeDriver();
            case FIREFOX:
                return getFirefoxDriver();
            default:
                throw new IllegalArgumentException("unknown driver type");

        }
    }

    private static WebDriver getChromeDriver() {
        setDriverProperty(CHROME_DRIVER_PROPERTY, CHROME_DRIVER);

        var options = new ChromeOptions();
        options.addArguments("--remote-debugging-port=9222");
        options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
        return new ChromeDriver(options);
    }

    private static WebDriver getFirefoxDriver() {
        setDriverProperty(FIREFOX_DRIVER_PROPERTY, FIREFOX_DRIVER);
        return new FirefoxDriver();
    }

    private static void setDriverProperty(String property, String executable) {
        var classLoader = DriverFactory.class.getClassLoader();
        var resource = Optional.ofNullable(classLoader.getResource(executable))
                .orElseThrow(() -> new RuntimeException("driver executable not found"));
        System.setProperty(property, resource.getPath());
    }

}

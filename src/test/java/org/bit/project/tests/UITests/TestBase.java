package org.bit.project.tests.UITests;

import org.bit.project.utils.MyListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.time.Duration;

public class TestBase {

    //Logger logger = LoggerFactory.getLogger(TestBase.class);
//        String browser = System.getProperty("browser", Browser.CHROME.browserName());
    public WebDriver driver;

    @BeforeMethod
    public void init() {
        System.err.close();
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/cms.html");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }
//    @BeforeMethod
//    public void startLogger() {
//        System.err.close();
//        if (browser.equalsIgnoreCase(Browser.CHROME.browserName())) {
//            driver = new ChromeDriver();
//            logger.info("All test run in Chrome Browser");
//        } else if (browser.equalsIgnoreCase(Browser.FIREFOX.browserName())){
//            driver = new FirefoxDriver();
//            logger.info("All test run in Firefox Browser");
//        }
//
//        WebDriverListener listener = new MyListener();
//        driver = new EventFiringDecorator(listener).decorate(driver);
//
//        driver.get("http://localhost:8080/cms.html");
//        logger.info("The link --> " + driver.getCurrentUrl());
//        logger.info("**********************************************************");
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//    }

    @AfterMethod
    public void tearDown() {
      driver.quit();
    }
}

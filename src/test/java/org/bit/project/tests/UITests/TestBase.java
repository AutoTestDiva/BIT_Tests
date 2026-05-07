package org.bit.project.tests.UITests;

import org.bit.project.utils.MyListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TestBase {
    Logger logger = LoggerFactory.getLogger(TestBase.class);
    String browser = System.getProperty("browser", Browser.CHROME.browserName());
    public WebDriver driver;

//    @BeforeMethod
//    public void init() {
//        System.err.close();
//        if (browser.equalsIgnoreCase(Browser.CHROME.browserName())) {
//            ChromeOptions options = new ChromeOptions();
//
//            // убрать сообщение "Chrome is being controlled..."
//            options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
//
//            // отключение сервисов Chrome
//            options.addArguments("--disable-notifications");
//            options.addArguments("--disable-popup-blocking");
//            options.addArguments("--disable-default-apps");
//            options.addArguments("--no-default-browser-check");
//
//            // отдельный профиль Selenium
//            options.addArguments("user-data-dir=C:/selenium/chrome-profile");
//
//            // настройки Chrome
//            Map<String, Object> prefs = new HashMap<>();
//
//            // отключить уведомления
//            prefs.put("profile.default_content_setting_values.notifications", 2);
//
//            // отключить менеджер паролей
//            prefs.put("credentials_enable_service", false);
//            prefs.put("profile.password_manager_enabled", false);
//
//            options.setExperimentalOption("prefs", prefs);
//            driver = new ChromeDriver(options);
//            logger.info("All tests run in Chrome Browser");
//        } else if (browser.equalsIgnoreCase(Browser.FIREFOX.browserName())) {
//            driver = new FirefoxDriver();
//            logger.info("All tests run in Firefox Browser");
//        } else if (browser.equalsIgnoreCase(Browser.EDGE.browserName())) {
//            driver = new EdgeDriver();
//            logger.info("All tests run in Edge Browser");
//        }
//        // Listener
//        WebDriverListener listener = new MyListener();
//        driver = new EventFiringDecorator(listener).decorate(driver);
//
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        driver.get("http://localhost:8080/cms.html");
//
//        logger.info("Open URL -> " + driver.getCurrentUrl());
//        logger.info("************************************************");
//    }
    @BeforeMethod
    public void startLogger(Method m) {
        logger.info("****************************************************************");
        logger.info("Start method --> " + m.getName());
    }
    @BeforeMethod
    public void startLogger() {
        System.err.close();
        if (browser.equalsIgnoreCase(Browser.CHROME.browserName())) {
            driver = new ChromeDriver();
            logger.info("All test run in Chrome Browser");
        } else if (browser.equalsIgnoreCase(Browser.FIREFOX.browserName())) {
            driver = new FirefoxDriver();
            logger.info("All test run in Firefox Browser");
        }else if (browser.equalsIgnoreCase(Browser.EDGE.browserName())) {
            driver = new EdgeDriver();
            logger.info("All tests run in Edge Browser");
        }

        WebDriverListener listener = new MyListener();
        driver = new EventFiringDecorator(listener).decorate(driver);

        driver.get("http://localhost:8080/cms.html");
        logger.info("The link --> " + driver.getCurrentUrl());
        logger.info("**********************************************************");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @BeforeMethod
    public void init() {
        System.err.close();
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/cms.html");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            logger.info("Closing browser");
            driver.quit();
            logger.info("Browser closed");
        }
    }
}
package org.bit.project.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    private final JavascriptExecutor js;
    WebDriver driver;

    public BasePage(WebDriver driver, JavascriptExecutor js) {
        this.driver = driver;
        this.js = js;
        PageFactory.initElements(driver, this);
    }

    public void click(WebElement element) {
        element.click();
    }

    public void type(WebElement element, String text) {
        if (text != null) {
            click(element);
            element.clear();
            element.sendKeys(text);
        }
    }

    public boolean shouldHaveText(WebElement element, String text, int time) {
        return new WebDriverWait(driver, Duration.ofSeconds(time))
                .until(ExpectedConditions.textToBePresentInElement(element, text));
    }
    public boolean isTextPresent(WebElement element, String text) {
        return element.getText().contains(text);
    }

    public boolean isElementPresent(WebElement element, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void scrollToElement(WebElement element) {
        if (js != null) {
            js.executeScript("arguments[0].scrollIntoView(true);", element);
        } else {
            System.err.println("JavascriptExecutor is not initialized!");
        }
    }

}

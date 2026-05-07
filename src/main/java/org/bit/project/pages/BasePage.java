package org.bit.project.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.HttpURLConnection;
import java.net.URL;
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
    public void clickWithJSExecutor(WebElement element, int x, int y){
        js.executeScript("window.scrollBy(" + x + "," + y + ")");
        element.click();
    }

    public void typeWithJSExecutor (WebElement element, String text, int x, int y){
        if (text != null) {
            clickWithJSExecutor(element, x, y);
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
    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void verifyLinks(String linkUrl) {
        try {
            //this is connection
            URL url = new URL(linkUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.connect();

            //this is result/response
            if (connection.getResponseCode() >= 400) {
                System.out.println(linkUrl + "-" + connection.getResponseMessage() + " is a brokenLink");
            } else {
                System.out.println(linkUrl + "-" + connection.getResponseMessage());
            }
        }catch (Exception ex){
            System.out.println(linkUrl + "-" + ex.getMessage() + " is a brokenLink");
        }
    }

    public void hideIframes() {
        hideAd();   //будет скрывать рекламу
        hideFooter(); //будет скрывать футер
    }

    public void hideFooter() {
        js.executeScript("document.querySelector('footer').style.display='none';");
    }

    public void hideAd() {
        js.executeScript("document.getElementById('adplus-anchor').style.display='none';");
        //.style.display='none' - именно это и скрывает рекламу на экране
    }
    protected String getValueAttribute(WebElement element, String name) {
        return element.getAttribute(name);  //возвращает значение аттрибута в виде строки
    }
}

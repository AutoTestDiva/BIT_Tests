package org.bit.project.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class CMSPage extends BasePage{
    public CMSPage(WebDriver driver, JavascriptExecutor js) {
        super(driver, js);
    }

    @FindBy(css= "h1")
    WebElement cmsElement;

    public CMSPage isCMSElementPresent(){
        Assert.assertTrue(isElementPresent(cmsElement, 10));
        return this;
    }


    @FindBy(xpath = "//body/button[1]")
    WebElement playButton;

    public CMSPage clickPlayButton(){
        click(playButton);
        return this;
    }

    }

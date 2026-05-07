package org.bit.project.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class CMSPage extends BasePage {
    public CMSPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "h1")
    WebElement cmsElement;

    public CMSPage isCMSElementPresent() {
        Assert.assertTrue(isElementPresent(cmsElement, 10));
        return this;
    }

    public CMSPage isCMSTextPresent(String text) {
        Assert.assertTrue(isTextPresent(cmsElement, text));
        return this;
    }

    @FindBy(xpath = "//body/button[1]")
    WebElement playButton;

    public CMSPage clickPlayButton() {
        click(playButton);
        return this;
    }
}

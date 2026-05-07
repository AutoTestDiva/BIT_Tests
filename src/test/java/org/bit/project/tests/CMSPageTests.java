package org.bit.project.tests;

import org.bit.project.pages.CMSPage;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CMSPageTests extends TestBase{


    @Test
    public void isCMSElementPresentPositiveTest() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        new CMSPage(driver, js)
                .isCMSElementPresent();
    }

    @Test
    public void playButtonPositiveTest() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        new CMSPage(driver, js)
                .clickPlayButton();
    }
}

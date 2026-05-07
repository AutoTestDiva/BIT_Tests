package org.bit.project.tests.UITests;

import org.bit.project.pages.CMSPage;
import org.testng.annotations.Test;

public class CMSPageTests extends TestBase {


    @Test
    public void isCMSElementPresentPositiveTest() {
        new CMSPage(driver)
                .isCMSElementPresent();
    }

    @Test
    public void isCMSTextPresentPositiveTest() {
        new CMSPage(driver)
                .isCMSTextPresent("CMS");
    }

    @Test
    public void playButtonPositiveTest() {
        new CMSPage(driver)
                .clickPlayButton();
    }
}

package com.demo.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.demo.utils.UtilitiesHelper;
import org.apache.commons.logging.impl.SLF4JLog;
import org.junit.Assert;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookmakerPage extends RegularWebPage{

    private static Logger LOGGER = LoggerFactory.getLogger(SLF4JLog.class);

    @FindBy(xpath = "//img[@class='bk-header__logo']")
    private SelenideElement loadChecker;

    @FindBy(xpath = "//section[@id='feedbacks']//span[@class='count']")
    private SelenideElement reviews;

    public void compareReviews() {
        Assert.assertEquals(UtilitiesHelper.vars.get("reviews"), reviews.getText());
        LOGGER.info("Ожидаемое количество отзывов равно фактическому количеству отзывов " + reviews.getText());
    }

    @Override
    public void checkIsPageLoaded() {
        loadChecker.shouldBe(Condition.visible);
    }
}

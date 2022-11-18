package com.demo.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.demo.utils.UtilitiesHelper;
import org.apache.commons.logging.impl.SLF4JLog;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.page;


public class BookmakersPage extends RegularWebPage {

    private static Logger LOGGER = LoggerFactory.getLogger(SLF4JLog.class);

    @FindBy(xpath = "//h2[contains(text(), 'Легальные букмекерские конторы')]")
    private SelenideElement loadChecker;

    @FindBy(xpath = "//table[contains(@class, 'bookmakers-rating-table')]//tr[@class='js-link']")
    private ElementsCollection bookmakers;

    public BookmakerPage selectBookmakerByBonusSum(String bonus) {
        SelenideElement result = bookmakers.filterBy(Condition.text(bonus)).last();
        SelenideElement nameElement = result.$(By.xpath(".//a[contains(@class, 'logo')]/img"));
        String reviews = result.$(By.xpath(".//a[contains(@class, 'icon-review')]")).getText();
        String name = nameElement.getAttribute("alt").replace("Логотип букмекерской конторы ", "")
                .replace(" - legalbet.ru", "");
        UtilitiesHelper.vars.put("reviews", reviews);
        LOGGER.info(String.format("Выбран букмекер %s. Представлено %s отзывов.", name, reviews));
        nameElement.click();
        return page(BookmakerPage.class);
    }

    public void checkIsPageLoaded() {
        loadChecker.shouldBe(Condition.visible);
    }
}

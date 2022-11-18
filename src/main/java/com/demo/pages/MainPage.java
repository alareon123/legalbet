package com.demo.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.actions;
import static com.codeborne.selenide.Selenide.page;

public class MainPage extends RegularWebPage{

    @FindBy(xpath = "//a[@class='site-logo ']")
    private SelenideElement loadChecker;

    @FindBy(xpath = "//nav[@class='mobile-aside-inner']/ul/li/a")
    private ElementsCollection listTopMenu;

    @FindBy(xpath = "//a[@href='/bukmekerskye-kontory/']/following-sibling::ul/li/a")
    private ElementsCollection bookmakerSubList;

    public MainPage openMenu(String menu) {
        actions().moveToElement(listTopMenu.find(Condition.text(menu))).build().perform();
        System.out.println("Выбран элемент верхнего меню с текстом - " + menu);
        return this;
    }

    public BookmakersPage openSubCategory(String subCategory) {
        bookmakerSubList.find(Condition.text(subCategory)).click();
        return page(BookmakersPage.class);
    }

    public void checkIsPageLoaded() {
        loadChecker.shouldBe(Condition.visible);
    }
}

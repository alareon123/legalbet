package com.demo.test.steps;

import com.codeborne.selenide.WebDriverRunner;
import com.demo.driver.WebDriverManager;
import com.demo.pages.BookmakerPage;
import com.demo.pages.BookmakersPage;
import com.demo.pages.MainPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class StepDefs {

    private String mainPageUrl = "https://legalbet.ru/";

    @Before
    public void before() {
        WebDriverManager.initChrome();
    }

    @After
    public void after() {
        WebDriverRunner.closeWebDriver();
    }

    @When("открыть главную страницу")
    public void openMainPage() {
        MainPage mainPage = open(mainPageUrl, MainPage.class);
        mainPage.checkIsPageLoaded();
    }

    @When("открыть меню {string} и выбрать подкатегорию {string}")
    public void openMenu(String menu, String subCategory) {
        MainPage mainPage = page(MainPage.class);
        mainPage.openMenu(menu)
                .openSubCategory(subCategory);
    }

    @When("выбрать последнего букмекера с бонусом равным {string}")
    public void selectLastBookmakerWithBonusEqualsTo(String bonus) {
        BookmakersPage bookmakersPage = page(BookmakersPage.class);
        bookmakersPage.checkIsPageLoaded();
        bookmakersPage.selectBookmakerByBonusSum(bonus);
    }

    @And("сравнить количество отзывов с предыдущей страницей")
    public void compareReviews() {
        BookmakerPage bookmakerPage = page(BookmakerPage.class);
        bookmakerPage.checkIsPageLoaded();
        bookmakerPage.compareReviews();
    }
}

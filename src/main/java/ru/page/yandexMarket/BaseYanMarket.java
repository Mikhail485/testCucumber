package ru.page.yandexMarket;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseYanMarket {
    private String selectorButtonCatalog = "//div[contains(@data-apiary-widget-name,'Entrypoint')]//button";

    protected WebDriverWait wait;
    protected WebDriver chromeDriver;

    protected WebElement elemHeaderSearch;
    protected WebElement elemCatalog;

    public BaseYanMarket(WebDriver chromeDriver, int waitSecond) {
        this.chromeDriver = chromeDriver;
        this.wait = new WebDriverWait(chromeDriver, waitSecond);
        elemHeaderSearch = chromeDriver.findElement(By.id("header-search"));
        elemCatalog = chromeDriver.findElement(By.xpath(selectorButtonCatalog));
    }
}

package ru.page.yandexMarket;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class YanMarketMainPage extends BaseYanMarket {
    private String selectorCategoryCatalog = "//li[@data-zone-name='category-link']";

    private List<WebElement> categoryCatalog;

    public YanMarketMainPage(WebDriver chromeDriver, int waitSecond) {
        super(chromeDriver, waitSecond);
    }

    public boolean openCatalog(String catalName){
        elemCatalog.click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(selectorCategoryCatalog)));
        categoryCatalog = chromeDriver.findElements(By.xpath(selectorCategoryCatalog));
        for (WebElement elem : categoryCatalog) {
            if (elem.getText().contains(catalName)){
                wait.until(ExpectedConditions.visibilityOfAllElements(categoryCatalog));
                elem.click();
                return true;
            }
        }
        return false;
    }
}

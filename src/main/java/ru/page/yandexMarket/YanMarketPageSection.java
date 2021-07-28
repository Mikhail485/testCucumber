package ru.page.yandexMarket;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class YanMarketPageSection extends BaseYanMarket {

    private String selectorSections = "//ul[@data-autotest-id='subItems']//a";

    private List<WebElement> categorySection;

    public YanMarketPageSection(WebDriver chromeDriver, int waitSecond){
        super(chromeDriver, waitSecond);
    }

    public boolean openSection(String nameSection){
        categorySection = chromeDriver.findElements(By.xpath(selectorSections));
        for (WebElement elem : categorySection){
            if (elem.getText().equals(nameSection)){
                elem.click();
                return true;
            }
        }
        return false;
    }
}

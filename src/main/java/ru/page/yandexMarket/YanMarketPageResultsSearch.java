package ru.page.yandexMarket;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class YanMarketPageResultsSearch extends BaseYanMarket {
    private String selectorDropDown = "//button[contains(@id,'dropdown-control')]";
    private String selectorButShowItem = "//div[contains(@aria-labelledby,'dropdown-control')]//button";
    private String selectorProduct = "//article[@data-autotest-id='product-snippet']";
    private String selectorProductName = ".//h3";
    private String selectorPriceFrom = "//input[@name='Цена от']";
    private String selectorPriceBefore = "//input[@name='Цена до']";
    private String selectorManufacturer = "//fieldset[@data-autotest-id=7893318]//label";
    private String selectorWait = "//div[@data-apiary-widget-id='/content/pager']";
    private String selectorNextPage = "//a[@aria-label='Следующая страница']";

    private WebElement elemDropDown;
    private WebElement elemPriceFrom;
    private WebElement elemPriceBefore;

    private List<WebElement> elemsManu;

    public YanMarketPageResultsSearch(WebDriver chromeDriver, int waitSecond) {
        super(chromeDriver, waitSecond);
        elemPriceFrom = chromeDriver.findElement(By.xpath(selectorPriceFrom));
        elemPriceBefore = chromeDriver.findElement(By.xpath(selectorPriceBefore));
        elemsManu = chromeDriver.findElements(By.xpath(selectorManufacturer));
    }

    public List<WebElement> show12Items() {
        elemDropDown = chromeDriver.findElement(By.xpath(selectorDropDown));
        elemDropDown.click();
        List<WebElement> listButShowItem = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(selectorButShowItem)));
        listButShowItem.get(0).click();
        return wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath(selectorProduct), 12));
    }


    public String getNameProduct(WebElement elem) {
        return elem.findElement(By.xpath(selectorProductName)).getAttribute("textContent");
    }

    public List<WebElement> searchProduct(String nameProduct){
        elemHeaderSearch.sendKeys(nameProduct, Keys.ENTER);
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(selectorProduct)));
    }

    public void priceLimit(String fromPrice, String beforePrice) {
        elemPriceFrom.sendKeys(fromPrice);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(selectorWait+"//a[contains(@href,'pricefrom="+fromPrice+"')]")));
        elemPriceBefore.sendKeys(beforePrice);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(selectorWait+"//a[contains(@href,'priceto="+beforePrice+"')]")));
    }

    public boolean manufacturer(String name) {
        for (WebElement elem : elemsManu){
            if (elem.getText().contains(name)){
                elem.click();
                String id = elem.findElement(By.xpath("./input")).getAttribute("id").split("_")[1];
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(selectorWait+"//a[contains(@href,'"+id+"')]")));
                return true;
            }
        }
        return false;
    }

    public List<String> getSampleProduct(){
        List<String> list = new ArrayList<>();
        int count = 2;
        do {
            List<WebElement> elements = chromeDriver.findElements(By.xpath(selectorProduct));
            for (WebElement element : elements) {
                list.add(element.findElement(By.xpath(".//h3")).getAttribute("textContent"));
            }
            chromeDriver.findElement(By.xpath(selectorNextPage)).click();
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[@aria-label='Страница "+count+" (текущая)']")));
            count++;
        }
        while (chromeDriver.findElements(By.xpath(selectorNextPage)).size() > 0);
        return list;
    }
}

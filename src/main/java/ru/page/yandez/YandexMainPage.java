package ru.page.yandez;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class YandexMainPage {

    private WebDriverWait wait;
    private WebDriver chromeDriver;

    protected WebElement elementOpenMarket;

    public YandexMainPage(WebDriver chromeDriver, int waitSecond){
        this.chromeDriver = chromeDriver;
        this.wait = new WebDriverWait(chromeDriver, waitSecond);
    }

    public boolean openYandexMarket(String nameService){
        elementOpenMarket = chromeDriver.findElement(By.linkText(nameService));
        elementOpenMarket.click();
        wait.until(ExpectedConditions.visibilityOf(elementOpenMarket));
        List<String> tabs = new ArrayList<>(chromeDriver.getWindowHandles());
        for (String tab : tabs){
            chromeDriver.switchTo().window(tab);
            if(chromeDriver.getTitle().contains(nameService))
                return true;
        }
        return false;
    }
}

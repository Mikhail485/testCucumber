package ru.page.yandexMarket.test;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.page.yandez.YandexMainPage;
import ru.page.yandexMarket.YanMarketPageResultsSearch;
import ru.page.yandexMarket.YanMarketPageSection;
import ru.page.yandexMarket.YanMarketMainPage;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Steps {
    WebDriver chromeDriver;

    @Step
    public void открытьХром(){
        System.setProperty("webdriver.chrome.driver",System.getenv("CHROME_DRIVER"));
        chromeDriver=new ChromeDriver();
        chromeDriver.manage().window().maximize();
        chromeDriver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        chromeDriver.manage().timeouts().setScriptTimeout(40, TimeUnit.SECONDS);
        chromeDriver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
    }

    @Step
    public void закрытьХром(){
        chromeDriver.quit();
    }

    @Step("Проверка открытия сервиса {name}")
    public void checkOpenService(YandexMainPage yanMain, String serviceName){
        Assertions.assertTrue(yanMain.openYandexMarket(serviceName), "Страница "+serviceName+" не найдена");
    }

    @Step("Проверка открытия раздел {nameSection}")
    public void checkOpenCatalog(YanMarketMainPage yanMarketMain, String nameSection){
        Assertions.assertTrue(yanMarketMain.openCatalog(nameSection), "Раздел "+nameSection+" не найден");
    }

    @Step("Проверка открытия раздела {nameSection}")
    public void checkOpenSection(YanMarketPageSection yanSection, String nameSection){
        Assertions.assertTrue(yanSection.openSection(nameSection), "Раздел "+nameSection+" не найден");
    }

    @Step("Проверяем есть ли товары с ценой от {fromPrice} до {beforePrice}")
    public void checkCriteriaPrice(YanMarketPageResultsSearch yanResultSearch, String fromPrice, String beforePrice){
       yanResultSearch.priceLimit(fromPrice, beforePrice);
    }

    @Step("Проверяем наличие у производителя {nameManuf}")
    public void checkManuf(YanMarketPageResultsSearch yanResultSearch, String nameManuf){
        Assertions.assertTrue(yanResultSearch.manufacturer(nameManuf), "Нет производителя "+nameManuf);
    }

    @Step("Проверяем, что на странице отобразилось 12 элементов")
    public void checkShowItems(List<WebElement> listResults){
        Assertions.assertEquals(12, listResults.size(), "на странице не отобразилось 12 элементов");
    }

    @Step("Проверяем, что наименование товара соответствует запомненному значению")
    public void checkEqualsProducts(String title1, String title2){
        Assertions.assertEquals(title1, title2);
    }

    @Step("Проверяе, что в выборку попали только {name}")
    public void checkContainsProducts(List<String> namesProduct, String name){
        Assertions.assertTrue(namesProduct.stream().allMatch(x -> x.contains(name)), "в выборку попали не только "+name);
    }
}

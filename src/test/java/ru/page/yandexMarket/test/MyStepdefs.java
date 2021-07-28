package ru.page.yandexMarket.test;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebElement;
import ru.page.yandez.YandexMainPage;
import ru.page.yandexMarket.*;

import java.util.List;

public class MyStepdefs extends Steps {

        YanMarketPageResultsSearch yanResultSearch;

        List<WebElement> listResults_1;
        List<WebElement> listResults_2;
        String nameFirstProduct;


        @Given("перейти на сайт '(.*)'")
        public void перейтиНаСайт(String namePage) {
                открытьХром();
                chromeDriver.get(namePage);
        }

        @Then("перейти в сервис '(.*)'")
        public void перейтиВСервисМаркет(String serviceName) {
                YandexMainPage yanMain = new YandexMainPage(chromeDriver, 30);
                checkOpenService(yanMain, serviceName);
        }

        @Then("выбрать раздел в каталоге '(.*)'")
        public void выбратьРазделВКаталоге(String nameSection) {
                YanMarketMainPage yanMarketMain = new YanMarketMainPage(chromeDriver, 30);
                checkOpenCatalog(yanMarketMain, nameSection);
        }

        @Then("выбрать раздел '(.*)'")
        public void выбратьРаздел(String nameSection) {
                YanMarketPageSection yanSection = new YanMarketPageSection(chromeDriver, 30);
                checkOpenSection(yanSection, nameSection);
        }

        @Then("установить цену от (.*) до (.*)")
        public void установитьЦену (String fromPrice, String beforePrice) {
                yanResultSearch = new YanMarketPageResultsSearch(chromeDriver, 30);
                checkCriteriaPrice(yanResultSearch, fromPrice, beforePrice);
        }

        @Then("выбрать производителя '(.*)'")
        public void выбратьПроизводителя (String nameManuf) {
                yanResultSearch = new YanMarketPageResultsSearch(chromeDriver, 30);
                checkManuf(yanResultSearch, nameManuf);
        }

        @Then("изменить результат поиска на 12 элементов и проверить изменения")
        public void изменитьРезультатыПоиска () {
                YanMarketPageResultsSearch yanResultSearch = new YanMarketPageResultsSearch(chromeDriver, 30);
                listResults_1 = yanResultSearch.show12Items();
                checkShowItems(listResults_1);
        }

        @Then("взять название первого элемента")
        public void взятьНазваниеПервогоЭлемента () {
                YanMarketPageResultsSearch yanResultSearch = new YanMarketPageResultsSearch(chromeDriver, 30);
                nameFirstProduct = yanResultSearch.getNameProduct(listResults_1.get(0));
        }

        @Then("сделать поиск по названию")
        public void поискПоНазванию () {
                listResults_2 = yanResultSearch.searchProduct(nameFirstProduct);
        }

        @Then("сравнить первые элементы поисков")
        public void сравнитьЭлементы () {
                YanMarketPageResultsSearch yanResultSearch = new YanMarketPageResultsSearch(chromeDriver, 30);
                checkEqualsProducts(nameFirstProduct, yanResultSearch.getNameProduct(listResults_2.get(0)));
        }

        @Then("проверить что в выборку попали только '(.*)'")
        public void проверкаВыборки(String name) {
                YanMarketPageResultsSearch yanResultSearch = new YanMarketPageResultsSearch(chromeDriver, 40);
                List<String> namesProduct = yanResultSearch.getSampleProduct();
                checkContainsProducts(namesProduct, name);

        }

        @Then("закончить работу")
        public void закрытьБраузер (){
                закрытьХром();
        }
}

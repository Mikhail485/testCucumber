package ru.page.yandexMarket.test;

import cucumber.api.Scenario;
import cucumber.api.java.After;

public class Hooks {
    @After
    public void getScenarioInfo(Scenario scenario) {
        System.out.println("____________________________");
        System.out.println(scenario.getId());
        System.out.println(scenario.getName());
        System.out.println(scenario.getStatus());
        System.out.println(scenario.isFailed());
        System.out.println(scenario.getSourceTagNames());
        System.out.println(scenario.getLines());
        System.out.println(scenario.getUri());
        System.out.println("____________________________");
    }
}

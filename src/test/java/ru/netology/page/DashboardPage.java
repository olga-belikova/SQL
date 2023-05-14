package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private SelenideElement head = $("[data-test-id=dashboard]");

    public DashboardPage() {
        head.shouldBe(visible);
    }
}

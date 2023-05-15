package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class VerificationPage {
    private final SelenideElement codeField = $("[data-test-id=code] input");
    private final SelenideElement verifyButton = $("[data-test-id=action-verify]");
    private final SelenideElement errorNotification = $("[data-test-id=error-notification]");

    public void verifyVerificationPageVisibility() {
        codeField.shouldBe(visible);
    }

    public void verifyErrorNotificationVisibility() {
        errorNotification.shouldBe(visible);
    }

    public void verifyBlockNotificationVisibility() {
        errorNotification
                .shouldBe(visible)
                .shouldHave(text("Вы три раза ввели неверный код! Пользователь заблокирован!"));
    }


    public DashboardPage validVerify(String verificationCode) {
        verify(verificationCode);
        return page(DashboardPage.class);
    }

    public void verify(String verificationCode) {
        codeField.setValue(verificationCode);
        verifyButton.click();
    }

}

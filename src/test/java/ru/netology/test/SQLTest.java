package ru.netology.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.SQLHelper.cleanDatabase;

public class SQLTest {
    @AfterAll
    static void teardown() {
      cleanDatabase();
    }

    LoginPage loginPage;

    @BeforeEach
    void setup() {
        loginPage = open("http://localhost:9999/", LoginPage.class);
    }

    @Test
    void shouldSuccessLogin() {
        var authInfo = DataHelper.getValidUser();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = SQLHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode.getCode());
    }

    @Test
    void shouldBlockAfter3InvalidCode() {
        var authInfo = DataHelper.getValidUser();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getRandomVerificationCode();
        verificationPage.verify(verificationCode.getCode());
        verificationPage.verify(verificationCode.getCode());
        verificationPage.verify(verificationCode.getCode());
        verificationPage.verifyBlockNotificationVisibility();
    }

}

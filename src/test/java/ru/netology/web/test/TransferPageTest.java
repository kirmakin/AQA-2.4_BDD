package ru.netology.web.test;

import com.codeborne.selenide.commands.Should;
import com.codeborne.selenide.commands.ShouldNot;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.TransferPage;

import static ru.netology.web.data.DataHelper.*;
import static ru.netology.web.page.DashboardPage.*;

import static com.codeborne.selenide.Selenide.open;


public class TransferPageTest {

    private int value1 = 200;
    private int value2 = 500;
    private int value3 = 11_000;


    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
        new LoginPage().validLogin(getAuthInfo()).validVerify(getVerificationCode(getAuthInfo()));
    }

    @Test
    void shouldTransferMoneyFromSecondToFirstCard() {
        var dashboard = new DashboardPage();
        dashboard.chooseFirstCardForTransfer();
        TransferPage.transaction(getSecondCardData(), Integer.toString(value1));
        int actualSecond = DashboardPage.getBalanceOfSecondCard();
        int actualFirst = DashboardPage.getBalanceOfFirstCard();
        int expectedFirst = getBalanceOfFirstCard();
        int expectedSecond = getBalanceOfSecondCard();
        assertEquals(expectedFirst, actualFirst);
        assertEquals(expectedSecond, actualSecond);
    }

    @Test
    void shouldTransferMoneyFromFirstToSecondCard() {
        var dashboard = new DashboardPage();
        dashboard.chooseSecondCardForTransfer();
        TransferPage.transaction(getFirstCardData(), Integer.toString(value2));
        int actualSecond = DashboardPage.getBalanceOfSecondCard();
        int actualFirst = DashboardPage.getBalanceOfFirstCard();
        int expectedFirst = getBalanceOfFirstCard();
        int expectedSecond = getBalanceOfSecondCard();
        assertEquals(expectedFirst, actualFirst);
        assertEquals(expectedSecond, actualSecond);
    }
    @Test
    void ShouldNotGoOutOfBeyonds(){
        var dashboard = new DashboardPage();
        dashboard.chooseFirstCardForTransfer();
        TransferPage.transaction(getSecondCardData(), Integer.toString(value3));
        int actualSecond = DashboardPage.getBalanceOfSecondCard();
        int actualFirst = DashboardPage.getBalanceOfFirstCard();
        int expectedFirst = getBalanceOfFirstCard();
        int expectedSecond = getBalanceOfSecondCard();
        assertEquals(expectedFirst, actualFirst);
        assertEquals(expectedSecond, actualSecond);
    }

}

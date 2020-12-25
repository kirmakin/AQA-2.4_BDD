package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.TransferPage;

import static ru.netology.web.data.DataHelper.*;
import static ru.netology.web.page.DashboardPage.*;

import static com.codeborne.selenide.Selenide.open;


public class TransferPageTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
        new LoginPage().validLogin(getAuthInfo()).validVerify(getVerificationCode(getAuthInfo()));
    }

    @Test
    void shouldTransferMoneyFromSecondToFirstCard() {
        int value = 200;
        var dashboard = new DashboardPage();
        int balanceOfFirstCardBefore = dashboard.getBalanceOfFirstCard();
        int balanceOfSecondCardBefore = dashboard.getBalanceOfSecondCard();
        val transferPage = dashboard.chooseFirstCardForTransfer();
        transferPage.transaction(getSecondCardData(), Integer.toString(value));
        int actualSecond = dashboard.getBalanceOfSecondCard();
        int actualFirst = dashboard.getBalanceOfFirstCard();
        int expectedFirst = DataHelper.checkBalanceOfCardTo(balanceOfFirstCardBefore, value);
        int expectedSecond = DataHelper.checkBalanceOfCardFrom(balanceOfSecondCardBefore, value);
        assertEquals(expectedFirst, actualFirst);
        assertEquals(expectedSecond, actualSecond);
    }

    @Test
    void shouldTransferMoneyFromFirstToSecondCard() {
        int value = 500;
        var dashboard = new DashboardPage();
        int balanceOfFirstCardBefore = dashboard.getBalanceOfFirstCard();
        int balanceOfSecondCardBefore = dashboard.getBalanceOfSecondCard();
        val transferPage = dashboard.chooseSecondCardForTransfer();
        transferPage.transaction(getFirstCardData(), Integer.toString(value));
        int actualSecond = dashboard.getBalanceOfSecondCard();
        int actualFirst = dashboard.getBalanceOfFirstCard();
        int expectedFirst = DataHelper.checkBalanceOfCardFrom(balanceOfFirstCardBefore, value);
        int expectedSecond = DataHelper.checkBalanceOfCardTo(balanceOfSecondCardBefore, value);
        assertEquals(expectedFirst, actualFirst);
        assertEquals(expectedSecond, actualSecond);
    }
    @Test
    void shouldNotGoOutOfBeyonds(){
        int value = 11_000;
        var dashboard = new DashboardPage();
        int balanceOfFirstCardBefore = dashboard.getBalanceOfFirstCard();
        int balanceOfSecondCardBefore = dashboard.getBalanceOfSecondCard();
        val transferPage = dashboard.chooseFirstCardForTransfer();
        transferPage.transaction(getSecondCardData(), Integer.toString(value));
        transferPage.errorTransaction();
        int actualSecond = dashboard.getBalanceOfSecondCard();
        int actualFirst = dashboard.getBalanceOfFirstCard();
        int expectedFirst = balanceOfFirstCardBefore;
        int expectedSecond = balanceOfSecondCardBefore;
        assertEquals(expectedFirst, actualFirst);
        assertEquals(expectedSecond, actualSecond);
    }

}

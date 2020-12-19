package ru.netology.web.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import lombok.val;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;

import static ru.netology.web.data.DataHelper.*;
import static ru.netology.web.page.DashboardPage.*;

import static com.codeborne.selenide.Selenide.open;


public class TransferPageTest {

    private int[] cardsBalance;
    private int value1 = 200;
    private int value2 = 11_000;

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
        new LoginPage().validLogin(getAuthInfo()).validVerify(getVerificationCodeFor(getAuthInfo()));
        cardsBalance = cardsBalance();
        cardsBalance = justifyBalance(cardsBalance[1], cardsBalance[2]);
        assertEquals(cardsBalance[1], cardsBalance[2]);
    }

    @AfterEach
    void asserting() {
        cardsBalance = cardsBalance();
        cardsBalance = justifyBalance(cardsBalance[1], cardsBalance[2]);
        assertEquals(cardsBalance[1], cardsBalance[2]);
    }

    @Test
    void shouldTransferMoneyBetweenOwnCards() {
        int expectedFirst = cardsBalance[1] + value1 - value1;
        int expectedSecond = cardsBalance[2] - value1 + value1;
        var dashboard = new DashboardPage();
        dashboard.moneyTransfer(cardNumber(1)).transaction(Integer.toString(value1), cardNumber(2));
        dashboard.moneyTransfer(cardNumber(2)).transaction(Integer.toString(value1), cardNumber(1));
        cardsBalance = cardsBalance();
        assertEquals(expectedFirst, cardsBalance[1]);
        assertEquals(expectedSecond, cardsBalance[2]);
    }

//    @Test
//    void shouldNotGoOutOfBounds() {
//        int expectedFirst = cardsBalance[1];
//        int expectedSecond = cardsBalance[2];
//        new DashboardPage().moneyTransfer(cardNumber(1)).transaction(Integer.toString(value1 + value2), cardNumber(2));
//        cardsBalance = cardsBalance();
//        assertEquals(expectedFirst, cardsBalance[1]);
//        assertEquals(expectedSecond, cardsBalance[2]);
//    }
}

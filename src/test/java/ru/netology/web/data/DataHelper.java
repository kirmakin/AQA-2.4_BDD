package ru.netology.web.data;

import com.codeborne.selenide.SelenideElement;
import com.sun.istack.NotNull;
import lombok.Value;
import ru.netology.web.page.TransferPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static ru.netology.web.page.DashboardPage.*;

import static ru.netology.web.page.DashboardPage.getHeading;

public class DataHelper {
    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @NotNull
    public static String cardNumber(int item) {
        String[] cards = {" ", "5559 0000 0000 0001", "5559 0000 0000 0002"};
        if (item >= 1 && item <= 2) {
            return cards[item];
        }
        return "0000 0000 0000 0000";
    }

    @NotNull
    public static int[] justifyBalance(int cardOne, int cardTwo) {
        getHeading().shouldBe(visible);
        int inequality = cardOne - cardTwo;
        int justifyValue = inequality / 2;
        if (inequality == 0) return cardsBalance();
        else if (inequality > 0) {
            getButtonForCard2().click();
            new TransferPage().transaction(Integer.toString(justifyValue), cardNumber(1));
            return cardsBalance();
        }
        if (inequality < 0) {
            getButtonForCard1().click();
            new TransferPage().transaction(Integer.toString(justifyValue * (-1)), cardNumber(2));
            return cardsBalance();
        }
        return cardsBalance();
    }

}



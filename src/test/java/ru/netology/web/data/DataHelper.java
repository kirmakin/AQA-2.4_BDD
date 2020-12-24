package ru.netology.web.data;

import lombok.Value;

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

    public static VerificationCode getVerificationCode(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class CardData {
        private String cardNumber;
        private String cardBalance;
    }

    public static CardData getFirstCardData() {
        return new CardData("5559000000000001", "10000");
    }

    public static CardData getSecondCardData() {
        return new CardData("5559000000000002", "10000");
    }

    public static int checkBalanceOfCardFrom (int balance, int amountForTransfer) {
        int finalBalance = balance - amountForTransfer;
        return finalBalance;

    }

    public static int checkBalanceOfCardTo (int balance, int amountForTransfer) {
        int finalBalance = balance + amountForTransfer;
        return finalBalance;
    }

}

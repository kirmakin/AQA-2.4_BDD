package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


public class DashboardPage {
    private static SelenideElement heading = $("[data-test-id=dashboard]");
    private static SelenideElement buttonForCard1 = $$("[data-test-id=action-deposit]").first();
    private static SelenideElement buttonForCard2 = $$("[data-test-id=action-deposit]").last();
    private static SelenideElement balanceForCard1 = $("[data-test-id=\"92df3f1c-a033-48e6-8390-206f6b1f56c0\"]");
    private static SelenideElement balanceForCard2 = $("[data-test-id=\"0f3f5c2a-249e-4c3d-8287-09f7a039391d\"]");

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public TransferPage chooseFirstCardForTransfer() {
        buttonForCard1.click();
        return new TransferPage();
    }

    public TransferPage chooseSecondCardForTransfer() {
        buttonForCard2.click();
        return new TransferPage();
    }

    public static int getBalanceOfFirstCard() {
        String selectedValue = balanceForCard1.getText();
        String balanceOfFirstCard = selectedValue.substring(29, selectedValue.indexOf(" ", 29));
        return Integer.parseInt(balanceOfFirstCard);
    }

    public static int getBalanceOfSecondCard() {
        String selectedValue = balanceForCard2.getText();
        String balanceOfFirstCard = selectedValue.substring(29, selectedValue.indexOf(" ", 29));
        return Integer.parseInt(balanceOfFirstCard);

    }
}

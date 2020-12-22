package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private static SelenideElement amountField = $(".money-input .input__control");
    private static SelenideElement fromCard = $("[data-test-id=from] .input__control");
    private static SelenideElement transferButton = $("[data-test-id=action-transfer] .button__text");

    public static void transaction(DataHelper.CardData fromCardData, String value) {
        amountField.doubleClick().sendKeys(Keys.BACK_SPACE, value.replace(" ", ""));
        fromCard.setValue(fromCardData.getCardNumber());
        transferButton.click();
    }

}

package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement AmountField = $(".money-input .input__control");
    private SelenideElement FromCard = $("[data-test-id=from] .input__control");
    private SelenideElement TransferButton = $("[data-test-id=action-transfer] .button__text");

    public void transaction(String value, String CardFrom) {
        AmountField.doubleClick().sendKeys(Keys.BACK_SPACE, value.replace(" ", ""));
        FromCard.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE, CardFrom.replace(" ", ""));
        TransferButton.click();
    }

}

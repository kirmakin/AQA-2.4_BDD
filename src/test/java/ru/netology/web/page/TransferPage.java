package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement amountField = $(".money-input .input__control");
    private SelenideElement fromCard = $("[data-test-id=from] .input__control");
    private SelenideElement transferButton = $("[data-test-id=action-transfer] .button__text");

    public void transaction(DataHelper.CardData fromCardData, String value) {
        amountField.doubleClick().sendKeys(Keys.BACK_SPACE, value.replace(" ", ""));
        fromCard.setValue(fromCardData.getCardNumber());
        transferButton.click();
    }
    public void errorTransaction(DataHelper.CardData fromCardData, String value) {
        amountField.doubleClick().sendKeys(Keys.BACK_SPACE, value.replace(" ", ""));
        fromCard.setValue(fromCardData.getCardNumber());
        transferButton.click();
        $("heading.shouldBe(visible).shouldHave(text('Ошибка! Недостаточно средств')");
    }

}

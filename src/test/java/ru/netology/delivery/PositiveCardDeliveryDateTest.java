package ru.netology.delivery;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PositiveCardDeliveryDateTest {

    public String needDate(LocalDate localDate) {
        int dayOfMonth = localDate.getDayOfMonth();
        int month = localDate.getMonthValue();
        int year = localDate.getYear();
        String rightDate;
        if (dayOfMonth < 10 && month < 10) {
            return String.format("0%d.0%d.%d", dayOfMonth, month, year);
        }
        if (dayOfMonth < 10 && month >=10) {
            return String.format("0%d.%d.%d", dayOfMonth, month, year);
        }
        if (dayOfMonth >= 10 && month < 10) {
            return String.format("%d.0%d.%d", dayOfMonth, month, year);
        } else {
            return String.format("%d.%d.%d", dayOfMonth, month, year);
        }
    }

    // TEST 1: DATE ON THE BOARD OF ALLOWABLE VALUE (+3 days after delivery date)
    @Test
    public void shouldLoadFormV1() {
        LocalDate localDate = LocalDate.now();
        localDate = localDate.plusDays(3);

        open("http://localhost:7777/");
        $("[data-test-id=city] input").setValue("Майкоп");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").doubleClick().setValue(needDate(localDate));
        $("[data-test-id=name] input").setValue("Немирович-Данченко Даниил");
        $("[data-test-id=phone] input").setValue("+79654321789");
        $("[data-test-id=agreement] .checkbox__box").click();
        $(".button").click();
        $("[data-test-id=notification]").shouldBe(Condition.appear, Duration.ofSeconds(15));
        $("[data-test-id=notification] .notification__content").shouldHave(Condition.ownText(needDate(localDate)));
    }

    // TEST 2: A LONG TIME AFTER DATE OF DELIVERY (+ one year after delivery date)
    @Test
    public void shouldLoadFormV2() {
        LocalDate localDate = LocalDate.now();
        localDate = localDate.plusYears(1);

        open("http://localhost:7777/");
        $("[data-test-id=city] input").setValue("Улан-Удэ");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").doubleClick().setValue(needDate(localDate));
        $("[data-test-id=name] input").setValue("Крылов ИИ");
        $("[data-test-id=phone] input").setValue("+79654321789");
        $("[data-test-id=agreement] .checkbox__box").click();
        $(".button").click();
        $("[data-test-id=notification]").shouldBe(Condition.appear, Duration.ofSeconds(15));
        $("[data-test-id=notification] .notification__content").shouldHave(Condition.ownText(needDate(localDate)));
    }

    // TEST 3: AVERAGE TIME AFTER A DATE OF DELIVERY (+ 2 month after delivery date)
    @Test
    public void shouldLoadFormV3() {
        LocalDate localDate = LocalDate.now();
        localDate = localDate.plusMonths(2);

        open("http://localhost:7777/");
        $("[data-test-id=city] input").setValue("Казань");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").doubleClick().setValue(needDate(localDate));
        $("[data-test-id=name] input").setValue("Крючкова Софья");
        $("[data-test-id=phone] input").setValue("+79654321789");
        $("[data-test-id=agreement] .checkbox__box").click();
        $(".button").click();
        $("[data-test-id=notification]").shouldBe(Condition.appear, Duration.ofSeconds(15));
        $("[data-test-id=notification] .notification__content").shouldHave(Condition.ownText(needDate(localDate)));
    }
}
